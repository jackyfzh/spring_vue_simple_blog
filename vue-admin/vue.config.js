// 请求转发代理
let proxyObj = {}; // 对象
//拦截http请求
proxyObj['/'] = {
    ws: false,  // 关闭websocket
    target: 'http://localhost:8080', // 转发地址
    changeOrigin: true,
    pathRewrite:{  // 地址重写
        '^/': ''  // 不修改
    }
}
// 应用导出
module.exports = {
    publicPath: './',
    devServer:{  // 配置开发环境
        host: 'localhost', // 端口号
        port: 4000,
        proxy: proxyObj,  // 代理对象
        allowedHosts: [
            'jblog.free.tryzth.com',
            'localhost:4001',
        ]
    }
}
