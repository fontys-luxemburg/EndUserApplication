const path = require("path");

module.exports = {
    outputDir: path.resolve(__dirname, "../webapp"),
    publicPath: '/enduser',
    devServer: {
        proxy: 'http://localhost:8080'
    },
}
