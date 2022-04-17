<template>
    <div>
        <!-- 文章列表 -->
        <el-table
            :data="blogsData"
            stripe
            style="width: 100%">
            <el-table-column label="文章列表">
            <template slot-scope="scope">
                <el-card  class="box-card">
                <div style="font-size: 18px;">
                    <!-- 判断是否为草稿 -->
                    <el-button size="mini" v-if="!scope.row.shareStatement" type="info" icon="el-icon-edit" circle></el-button>
                    {{scope.row.title}}
                </div>
                <div style="margin-top: 10px;">
                    <el-tag size="small" style="margin-right: 10px;"  v-if="scope.row.isDelete" type="danger">已删除</el-tag>
                    <el-tag size="small" style="margin-right: 10px;"  v-if="!scope.row.shareStatement" type="warning">草稿</el-tag>
                    <el-tag style="margin-right: 10px;" v-if="scope.row.shareStatement" size="small">{{scope.row.flag}}</el-tag>
                    <el-tag style="margin-right: 20px;" v-if="scope.row.shareStatement" size="small" type="info">{{scope.row.published == '0' ? '私密' : '公开'}}</el-tag>
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
    </div>
</template>

<script>
export default {
    name: 'SearchBlogsCom',
    data () {
    return {
        blogsData: [], // 文章数据
    }
},
    props:["blogsData"], // 接收父组件的传值
    methods:{

    }
}
</script>

<style scoped>

</style>