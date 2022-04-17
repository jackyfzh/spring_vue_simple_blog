<template>
    <div>
        <!-- 文章列表 -->
        <el-table :data="blogsData" stripe style="width: 100%">
            <el-table-column label="文章列表">
            <template slot-scope="scope">
                <el-card  class="box-card">
                <div style="font-size: 18px;">
                    <!-- 判断文章是否为草稿 -->
                    <el-button size="mini" v-if="!scope.row.shareStatement" type="info" icon="el-icon-edit" circle></el-button>
                    {{scope.row.title}}
                </div>
                <div style="margin-top: 10px;">
                    <el-tag size="small" style="margin-right: 10px;"  v-if="scope.row.isDelete" type="danger">已删除</el-tag>
                    <el-tag size="small" style="margin-right: 10px;"  v-if="!scope.row.shareStatement" type="warning">草稿</el-tag>
                    <el-tag style="margin-right: 10px;" v-if="scope.row.shareStatement" size="small">{{scope.row.flag}}</el-tag>
                    <el-tag style="margin-right: 20px;" v-if="scope.row.shareStatement" size="small" type="info">{{scope.row.published == '0' ? '私密' : '公开'}}</el-tag>
                    <!-- <el-tag style="margin-right: 20px;" v-if="scope.row.shareStatement" size="small" type="success">{{scope.row.typeId}}</el-tag> -->
                    <i style="margin-right: 20px;" class="el-icon-view"> {{scope.row.views}} </i>
                    <i style="margin-right: 20px;" class="el-icon-chat-square"> {{scope.row.commentCount}} </i>
                    <i style="margin-right: 20px;" class="el-icon-date"> {{scope.row.createTime}}</i>
                    <el-button style="float: right;" type="danger" size="mini">删除</el-button>
                    <el-button style="float: right; margin-right: 10px;" type="primary" size="mini">编辑</el-button>
                </div>
                </el-card>
            </template>  
            </el-table-column>
        </el-table>
        <!-- 分页 -->
        <div v-if="!hidden_page" style="margin-top: 20px;">
                <el-pagination
                background
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="pagesizes"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
                </el-pagination>
        </div>
    </div>
</template>

<script>
export default {
    name: 'BlogListCom',
    data () {
    return {
    blogsData: [], // 文章数据
    currentPage: 1,  // 当前页
    total:0, // 总记录数
    pagesize:10, // 页面大小
    pagesizes:[10,20,30], // 页面数组
    t2index:0, // 选项卡index
    }
},
    props:["tindex"],
    mounted() {
        this.initIndex();
        this.initBlogs(); // 调用博客数据
    },
    methods:{
    initIndex(){
        this.t2index = this.tindex  // 保存父组件的传值
    },
    // 初始化数据
    initBlogs(){
        const _this = this
        // 通用路由
        var baseurl = '/blog/getByPage?current=' + this.currentPage + '&size=' + this.pagesize
        // 拼接路由
      if(this.t2index == "0"){ // 全部
        baseurl +=' &is_delete=0'
        }
      if(this.t2index == "1"){ // 原创
        baseurl +=' &flag=原创 &share_statement=1  &is_delete=0'
        }
      if(this.t2index == "2"){ // 转载
        baseurl +=' &flag=转载 &share_statement=1  &is_delete=0'
        }
      if(this.t2index == "3"){  //草稿
        baseurl +=' &share_statement=0  &is_delete=0'
        }
      if(this.t2index == "4"){ // 公开
        baseurl +=' &published=1 &share_statement=1  &is_delete=0'
        }
      if(this.t2index == "5"){ // 私密
        baseurl +=' &published=0 &share_statement=1 &is_delete=0'
        }
      if(this.t2index == "6"){ // 回收站
        baseurl +=' &is_delete=1 &share_statement=1  &is_delete=0'
        }
        // console.log(baseurl)
        this.getRequest(baseurl).then(resp=>{
        if(resp){
            console.log(resp)
            _this.blogsData = resp.obj.records  // 赋值
            _this.total = resp.obj.total   // 保存
            }
        })
    },
    // 当前页
    handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage = val
        this.initBlogs()
    },
    //每页条数
    handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.pagesize = val
        this.initBlogs()
    },
}
}
</script>

<style scoped>

</style>