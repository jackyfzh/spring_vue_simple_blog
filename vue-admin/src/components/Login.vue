<template>
    <div>
        <el-form :rules="rules" :model="loginForm" class="loginContainer" ref="loginForm">
            <h3 class="loginTitle">Jacky的个人博客后台登录</h3>
            <el-form-item prop="username">
                <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="用户名" @keydown.enter.native="submitLogin"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码" @keydown.enter.native="submitLogin"></el-input>
            </el-form-item>
            <el-checkbox class="loginRember" v-model="checked">记住密码</el-checkbox>
            <el-button type="primary" style="width: 100%" @click="submitLogin">登录</el-button>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data(){
            return{
                loginForm:{
                    // 用户名密码
                    username:"admin",
                    password:"123"
                },
                checked:true,
                rules:{
                    // 内容不为空
                    username:[{required:true,message:"用户名不能为空",trigger:"blur"}],
                    password:[{required:true,message:"密码不能为空",trigger:"blur"}],
                }
            }
        },
        methods:{
            submitLogin(){
                this.$refs.loginForm.validate((validate) =>{  // 自带校验
                    if(validate){
                        // 封装api
                        this.postKeyValueRequest('/doLogin',this.loginForm).then(resp=>{
                            if(resp){
                                window.sessionStorage.setItem("user", JSON.stringify(resp.obj)); // 储存sessionStorange
                                this.$router.replace('/admin/home')
                            }
                        })
                    }else {
                        this.$message.error("请输入所有字段");
                        return false;
                    }
                })
            }
        }

    }
</script>

<style scoped>
    .loginContainer{
        border-radius: 15px;
        background-clip:padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .loginTitle{
        margin: 15px auto 20px auto;
        text-align: center;
        color: #505458;
    }
    .loginRember{
        text-align: left;
        margin: 0 0 25px 0;
    }
</style>