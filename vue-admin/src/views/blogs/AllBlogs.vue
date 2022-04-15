<template>
<div>
    <el-tabs v-model="activeName" @tab-click="handleClick" style="margin-top: 10px;">
        <el-tab-pane label="全部（120）" name="first">
        <!-- 搜索栏 -->
        <el-input size="small" v-model="input_title" placeholder="请输入标题，可回车搜索..." prefix-icon="el-icon-search"
        style="width: 400px;margin-right: 10px;"></el-input>
        <el-button size="small" type="primary" icon="el-icon-search">搜索</el-button>
        <el-button size="small" type="primary">高级搜索</el-button>

        <!-- 博客文章 -->
        <el-table :data="blogsData" stripe style="width: 100%">
        <el-table-column label="文章列表">
            <template slot-scope="scope">
            <el-card  class="box-card">
            <div style="font-size: 18px;">
                <!-- 判断是否为草稿-->
                <el-button size="mini" v-if="!scope.row.shareStatement" type="info" icon="el-icon-edit" circle></el-button>
                {{scope.row.title}}
            </div>
            <div style="margin-top: 10px;">
                <!--其他相关数据-->
                <el-tag size="small" style="margin-right: 10px;"  v-if="!scope.row.shareStatement" type="warning">草稿</el-tag>
                <el-tag style="margin-right: 10px;" v-if="scope.row.shareStatement" size="small">{{scope.row.flag}}</el-tag>
                <el-tag style="margin-right: 10px;" v-if="scope.row.shareStatement" size="small" type="info">{{scope.row.published}}</el-tag>
                <el-tag style="margin-right: 20px;" v-if="scope.row.shareStatement" size="small" type="success">{{scope.row.typeId}}</el-tag>
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
    <div style="margin-top: 20px;">
            <!-- 分页 -->
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

    </el-tab-pane>
        <el-tab-pane label="原创（20）" name="second">原创（20）</el-tab-pane>
        <el-tab-pane label="转载（20）" name="third">转载（20）</el-tab-pane>
        <el-tab-pane label="草稿（20）" name="fourth">草稿（20）</el-tab-pane>
        <el-tab-pane label="公开（50）" name="fifth">公开（50）</el-tab-pane>
        <el-tab-pane label="私密（10）" name="sixth">私密（10）</el-tab-pane>
        <el-tab-pane label="回收站（20）" name="serven">回收站（20）</el-tab-pane>
    </el-tabs>
</div>
</template>

<script>
export default {
name: 'AllBlogs',
data () {
    return {
    activeName: 'first',
    input_title: '', //输入框的值
    // 测试数据
    blogsData: [{   //文章数据
        title: '用于展示多条结构类似的数据，可对数据进行排序、筛选、对比或其他自定义操作',
        flag: '原创',
        published:'公开',
        createTime:'2020年12月07日 12:00:00',
        views:'999',
        commentCount:'99',
        typeId:'springboot学习',
        shareStatement:false,
    }, {
        title: '用于展示多条结构类似的数据，可对数据进行排序、筛选、对比或其他自定义操作',
        flag: '原创',
        published:'公开',
        createTime:'2020年12月07日 12:00:00',
        views:'999',
        commentCount:'99',
        typeId:'springboot学习',
        shareStatement:true,
}],
      currentPage: 1,  //当前页
      total:100, //总记录数
      pagesize:10, //页面大小
      pagesizes:[10,20,30], //页面数组

    }
},
methods:{
    handleClick(tab, event) {
        console.log(tab.index);
    },
    // 分页的当前页
    handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
    },
    //每页多少条
    handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
    },
}
}
</script>

<style scoped>

</style>