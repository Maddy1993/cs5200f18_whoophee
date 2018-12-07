const mongoose = require('mongoose');
const trueFalseSchema = mongoose.Schema({
    isTrue: Boolean
});
module.exports = trueFalseSchema;