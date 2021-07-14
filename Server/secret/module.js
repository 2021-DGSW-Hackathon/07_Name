const mysql = require('mysql');

const pool = mysql.createConnection({
    host : '34.64.94.85',
    port : 3306,
    user : "user",
    password : "1234",
    database : "main",
    connectionLimit : 5,
})

module.exports = pool;