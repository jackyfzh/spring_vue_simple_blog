import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/admin',
    name: 'Login',
    component: Login,
    hidden: true
  },
  {
    path: '/admin',
    name: 'Login',
    component: Login,
    hidden: true
  },
  {
    path: '/admin/home',
    name: 'Home',
    component: Home
  },
  
]

const router = new VueRouter({
  routes
})

export default router