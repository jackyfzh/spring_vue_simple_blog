import axios from 'axios'
import { Message } from 'element-ui'
import router from '../router'

axios.interceptors.response.use(success => { // 成功处理
    if(success.status && success.status == 200 && success.data.status == 500 ){
        Message.error({message: success.data.msg}) // 后端错误提示
        return;
    }
    if(success.data.msg){
        Message.success({message: success.data.msg}) // 后端正确提示
    }
    return success.data
},error =>{ // 失败处理
    if(error.response.status == 504 || error.response.status == 404){
        Message.error({message: "504/404 无法访问"})
    }else if(error.response.status == 403){
        Message.error({message: "403 无权访问"})
    }else if(error.response.status == 401){
        Message.error({message: "401 尚未登录"})
        router.replace('/')
    }else{
        // 错误信息
        if(error.response.data.msg){
            Message.error({message: error.response.data.msg})
        }else{
            Message.error({message: "未知的错误"})
        }
    }
    return;
})

// 封装请求
let base = ''; 
export const postKeyValueRequest=(url,params)=>{

    return axios({
        method: 'post',  
        url: `${base}${url}`, // 引用变量
        data: params, // json形式
        transformRequest: [function (data){ // 进行处理
            let ret = '';
            for(let i in data){
               // 追加变量 
                ret+=encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&'
            }
            console.log(ret);
            return ret;
        }],
        // 定义请求头
        headers:{
            'Content-Type':'application/x-www-form-urlencoded '
        }

    })

}

