const mongoose = require("mongoose");
const Story = require("./models/Story");

const connect = () => {
  return mongoose.connect(
    "mongodb://serverless:serverless@ds239128.mlab.com:39128/serverless"
  );
};

const createResponse = (status, body) => ({
  statusCode: status,
  body: JSON.stringify(body)
});

exports.createStory = (event, ctx, cb) => {
  ctx.callbackWaitsForEmptyEventLoop = false;
  const { title, body } = JSON.parse(event.body);
  connect()
    .then(() => {
      const story = new Story({ title, body });
      return story.save();
    })
    .then(story => {
      cb(null, createResponse(200, story));
    })
    .catch(e => {
      e => cb(e);
    });
};

exports.readStories = (event, ctx, cb) => {
  cb(null, createResponse(200, { message: "list" }));
};

exports.readStory = (event, ctx, cb) => {
  cb(null, createResponse(200, { message: "read" }));
};

exports.updateStory = (event, ctx, cb) => {
  cb(null, createResponse(200, { message: "update" }));
};

exports.deleteStory = (event, ctx, cb) => {
  cb(null, createResponse(200, { message: "delete" }));
};
