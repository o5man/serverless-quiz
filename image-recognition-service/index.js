let AWS = require("aws-sdk");
let s3 = new AWS.S3({apiVersion: "2006-03-01"});
let rekognition = new AWS.Rekognition();
let docClient = new AWS.DynamoDB.DocumentClient();
const uuidv4 = require('uuid/v4');

let lambdaCallback, bucket, key;

exports.handler = function(event, context, callback) {
  lambdaCallback = callback
  bucket = event.Records[0].s3.bucket.name;
  key = event.Records[0].s3.object.key;
  rekognizeLabels(bucket, key)
    .then(function(data) {
      labelData = data["Labels"];
      return addDataToTable()
    }).then(function(data) {
      lambdaCallback(null, data)
    }).catch(function(err) {
      lambdaCallback(err, null);
    });
};

function rekognizeLabels(bucket, key) {
  let params = {
    Image: {
      S3Object: {
        Bucket: bucket,
        Name: key
      }
    },
    MaxLabels: 3,
    MinConfidence: 80
  };
  return rekognition.detectLabels(params).promise()
};

function addDataToTable() {
  let labels = []
  labelData.forEach(function(label) {
    labels.push(label.Name)
  });
  var url = "/" + bucket + "/" + key;
  console.log("url: " + url);
  let params = {
    TableName: "Quiz_Images",
    Item: {
      Id: uuidv4(),
      filename: key.split(".")[0],
      timestamp: new Date().getTime(),
      url: url,
      labels: labels
    }
  };
  return docClient.put(params).promise()
};
