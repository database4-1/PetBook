var mysql = require('mysql');

var connection = mysql.createConnection({
    host : 'localhost',
    port : 3306,
    user : 'root',
    password : '51168496',
    database : 'petbook'
})

module.exports = connection;