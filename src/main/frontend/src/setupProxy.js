const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    ["/react-api", "/games/", "/css"],
    createProxyMiddleware({
      target: "http://localhost:8080",
      changeOrigin: true,
      xfwd: true,
    })
  );
};
