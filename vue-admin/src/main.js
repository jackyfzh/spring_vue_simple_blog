import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import { Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import {postKeyValueRequest} from "./utils/api";
import {postRequest} from "./utils/api";
import {putRequest} from "./utils/api";
import {getRequest} from "./utils/api";
import {deleteRequest} from "./utils/api";

Vue.prototype.postKeyValueRequest = postKeyValueRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.config.productionTip = false
Vue.use(ElementUI);

router.beforeEach((to,from,next)=>{
  if(to.path=='/' || to.path=='/admin'){ // 路由守卫
    next();
  }else{
    if(window.sessionStorage.getItem("user")){ // 已登录
        next();
    }else{ // 未登录
      Message.error({message: "尚未登录，请先登录"})
      next('/?redirect=' + to.path);
    }
  
  }
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')