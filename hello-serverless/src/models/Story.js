const mongoose = require("mongoose");

const StorySchema = new mongoose.Schema({
  title: String,
  body: String
});

// Cannot overwrite model once compiled.
global.Story = global.Story || mongoose.model("Story", StorySchema);
module.exports = global.Story;
