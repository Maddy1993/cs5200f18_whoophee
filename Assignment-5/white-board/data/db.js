module.exports = function () {
    const mongoose = require('mongoose');
    const databaseName = 'white-board';
    let connectionString = 'mongodb://localhost/';
    connectionString += databaseName;
    mongoose.connect(connectionString);
};
