<template>
    <div>
    <!-- 搜索栏 -->
    <el-tabs v-model="activeName" @tab-click="handleClick" style="margin-top: 10px;">
        <el-tab-pane label="全部（120）" name="first">
        <!-- 搜索 -->
        <el-input size="small" v-model="input_title" placeholder="请输入标题，可回车搜索..." prefix-icon="el-icon-search"
        style="width: 400px;margin-right: 10px;" @keydown.enter.native="search_title"></el-input>
        <el-button size="small" type="primary"  @click="search_title" icon="el-icon-search">搜索</el-button>
        <el-button size="small" type="primary">高级搜索</el-button>
        <!-- 通用博客列表组件 -->
        <BlogListCom :tindex="tabindex" v-if="tabindex == 0 && hidden_searchcom == false"></BlogListCom>
        <!-- 搜索专用组件 -->
        <SearchBlogsCom v-if="tabindex == 0 && hidden_searchcom == true" :blogsData="blogsData"></SearchBlogsCom>
    </el-tab-pane>
    <!-- 标签栏 -->
    <el-tab-pane label="原创（20）" name="second">
        <BlogListCom :tindex="tabindex" v-if="tabindex == 1"></BlogListCom>
    </el-tab-pane>
    <el-tab-pane label="转载（20）" name="third">
        <BlogListCom :tindex="tabindex" v-if="tabindex == 2"></BlogListCom>
    </el-tab-pane>
        <el-tab-pane label="草稿（20）" name="fourth">
        <BlogListCom :tindex="tabindex" v-if="tabindex == 3"></BlogListCom>
    </el-tab-pane>
        <el-tab-pane label="公开（50）" name="fifth">
        <BlogListCom :tindex="tabindex" v-if="tabindex == 4"></BlogListCom>
    </el-tab-pane>
        <el-tab-pane label="私密（10）" name="sixth">
        <BlogListCom :tindex="tabindex" v-if="tabindex == 5"></BlogListCom>
    </el-tab-pane>
    <el-tab-pane label="回收站（20）" name="serven">
        <BlogListCom :tindex="tabindex" v-if="tabindex == 6"></BlogListCom>
    </el-tab-pane>
</el-tabs>
</div>
</template>

<script>
// 导入子组件
import BlogListCom from '@/components/blogs/BlogListCom'
import SearchBlogsCom from '@/components/blogs/SearchBlogsCom'
export default {
    name: 'AllBlogs',
    components:{ BlogListCom, SearchBlogsCom }, //注册组件
    data () {
    return {
        activeName: 'first',
        input_title: '', // 输入框的值
        blogsData: [], // 文章数据
        tabindex:0, // 选项卡索引
        hidden_searchcom: false , //是否隐藏搜索子组件
    }
},
    mounted() {
    
    },
    methods:{
    // 获取标签栏索引
    handleClick(tab, event) {
        this.tabindex = tab.index
        console.log("tabindex = " + this.tabindex);
    },
    // 根据标题搜索
    search_title(){
        // 判断子组件
        if(this.input_title == ''){
            // 判断为空
            this.hidden_searchcom = false
        }else{
            //如果搜索值不为空，就显示搜索专用子组件
            this.hidden_searchcom = true
        }
        const _this = this
        //根据标题搜索请求
        this.getRequest('/blog/getByTitle?title=' + this.input_title).then(resp=>{
        if(resp){
            _this.blogsData = resp.obj
        }
    })
    }
    }
}
</script>

<style scoped>

</style>