<template>
    <div>
        <el-container>
            <el-header class="homeHeader">
                <a href="/#/admin/home"><div class="title">Jacky的个人博客后台</div></a>
                <el-dropdown class="userInfo" @command="commandHandler">  <!--事件回调-->
                    <span class="el-dropdown-link">
                        {{user.name}}
                        <i><img :src="user.avatar" alt=""></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="userInfo">个人中心</el-dropdown-item>
                        <el-dropdown-item command="setting">设置</el-dropdown-item>\
                        <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu  router>
                        <el-menu-item index="/admin/home">
                            <i class="el-icon-menu"></i>
                            <span slot="title">首页</span>
                        </el-menu-item>
                        <!--遍历数组-->
                        <el-submenu :index="item.path" v-for="(item,index) in this.$router.options.routes" v-if="!item.hidden" :key="index">   
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>{{item.name}}</span>
                            </template>
                            <el-menu-item :index="child.path" v-for="(child,indexj) in item.children" v-if="!child.hidden" :key="indexj">
                                {{child.name}}
                            </el-menu-item>                        
                        </el-submenu>
                    </el-menu>
                </el-aside>

                <el-main>
                    <!-- 面包屑 -->
                    <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path!='/admin/home'">
                        <el-breadcrumb-item :to="{ path: '/admin/home' }">首页</el-breadcrumb-item>
                        <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
                    </el-breadcrumb>
                    <div class="homeWelcome" v-if="this.$router.currentRoute.path=='/admin/home'">
                        Hello World!
                    </div>
                    <router-view/>
                </el-main>

            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Home",
        data(){
            return{
                user:JSON.parse(window.sessionStorage.getItem("user"))  //数据转换
            }
        },
        methods:{
            commandHandler(cmd){
                if(cmd=='logout'){
                    // 判断是否登出
                    this.$confirm('此操作将注销登录, 是否继续?', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(() => {
                            this.getRequest("/logout");
                            window.sessionStorage.removeItem("user")
                            this.$router.replace("/")
                        
                        }).catch(() => {
                            this.$message({
                                type: 'info',
                                message: '已取消注销'
                            });          
                        });
                }
            }
        }
    }
</script>

<style>
    .homeHeader{
        background-color:#4a9ff4;
        display: flex;
        align-items: center; /*竖轴上居中*/
        justify-content:space-between; /*空白的地方在中间*/
        padding: 0 15px;
        box-sizing: border-box;
    }
    /* 字体样式 */
    .title{
        font-size: 30px;
        font-family: 华文行楷;
        color: #ffffff;
    }
    /*设置鼠标移上去显示为手指*/
    .userInfo{
        cursor: pointer; 
    }
    /* 头像样式 */
    .el-dropdown-link img{
        width: 48px;
        height: 48px;
        border-radius: 24px;
        margin-left: 8px;
    }
    /* 用户名样式 */
    .el-dropdown-link{
        display: flex;
        align-items: center;
    }
    /* 面包屑样式 */
    .homeWelcome{
        text-align: center;
        font-size: 30px;
        font-family: 华文行楷;
        color: #4a9ff4;
        padding-top: 50px;
    }
    a{
        text-decoration:none;
    }


</style>
