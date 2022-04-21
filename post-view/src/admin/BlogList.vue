<template>
  <div class="bloglist-contain">

    <!--Post list-->
    <el-table :data="blogList">
      <el-table-column label="Sequence" type="index" width="50"></el-table-column>
      <el-table-column label="Title" prop="title" show-overflow-tooltip></el-table-column>
      <el-table-column label="Classification" prop="typeName" width="150"></el-table-column>
      <el-table-column label="Created time" width="170">
        <template v-slot="scope">{{ scope.row.createTime }}</template>
      </el-table-column>
      <el-table-column label="updated date" width="170">
        <template v-slot="scope">{{ scope.row.updateTime }}</template>
      </el-table-column>

      <!--delete and edit function-->
      <el-table-column label="Operation" width="200">
        <template v-slot="scope">
          <el-button icon="el-icon-edit" size="mini" type="primary" @click="goBlogEditPage(scope.row.id)">edit</el-button>
          <el-popconfirm icon="el-icon-delete" iconColor="red" title="Are you sure to delete？" @confirm="deleteBlogById(scope.row.id)">
            <el-button slot="reference" icon="el-icon-delete" size="mini" type="danger">delete</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!--pagination-->
    <div  class="home-page">
      <!--pagination-->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum"
                     :page-sizes="[10, 20, 30, 50]" :page-size="queryInfo.pageSize" :total="total"
                     layout="total, sizes, prev, pager, next, jumper" background>
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "BlogList",
  data() {
    return {
      queryInfo: {
        title: '',
        categoryId: null,
        pageNum: 1,
        pageSize: 10
      },
      ifShow: false,
      blogList: [],
      total: 0,
      types: []
    }
  },
  created() {
    this.getTypes()
    this.getData();

  },
  methods: {
    //jump to the post edit interface
    goBlogEditPage(blogId) {
      this.$router.push(`/blog/edit/${blogId}`)
    },
    //gain the post type
    getTypes() {
      const _this = this
      this.$axios.get('/types').then(res => {
        _this.types = res.data.data
      })
      //console.log(this.types)
    },
    //gain the current pages' post
    getData() {
      const _this = this
      this.$axios.get('/blogList?currentPage=' + this.queryInfo.pageNum+"&pageSize=" + this.queryInfo.pageSize,{
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then((res) => {
        _this.blogList = res.data.data.records
        _this.total = res.data.data.total
        for (var i in _this.blogList) {
          for (var j in _this.types) {
            if (_this.blogList[i].typeId == _this.types[j].id) {
              _this.blogList[i].typeName = _this.types[j].typeName
            }
          }
        }

      });

    },
    //delete the post
    deleteBlogById(blogId) {
      const _this = this
      this.$axios.get('/blog/delete/' + blogId).then((res) => {
        _this.$alert('Operation successfully', 'Notification', {
          confirmButtonText: 'Confirm',
          callback: action => {


            _this.getData()
          }
        })
      })
    },

    handleSizeChange(newPageSize) {
      this.queryInfo.pageSize = newPageSize;
      this.getData();

    },
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage
      this.getData()
    },

  }
}
</script>
<style scoped>
.el-button + span {
  margin-left: 10px;
}
</style>
