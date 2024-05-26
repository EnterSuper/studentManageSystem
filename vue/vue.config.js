const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8090  // 前端项目端口，与后端的端口不一样，二者一个是前端项目的端口，一个是后端项目的端口
  },
  chainWebpack: config =>{
    config.plugin('html')
        .tap(args => {
          args[0].title = "Pjkang";
          return args;
        })
  }
})