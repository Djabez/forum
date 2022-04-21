<template>
  <div>
    <div style="text-align: right">
      <el-button title="add friend chain"  type="primary" size="medium" icon="el-icon-plus" @click="addDialogVisible=true" circle></el-button>
    </div>
    <el-table
        border
        :data="friendList"
        style="width: 90%" default-sort="{prop: 'nickname', order: 'descending'}">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="nickename">
              <span>{{ props.row.nickname }}</span>
            </el-form-item>
            <el-form-item label="description">
              <span>{{ props.row.description }}</span>
            </el-form-item>
            <el-form-item label="website">

              <el-link :href="props.row.website" target="_blank">{{ props.row.website }}</el-link>
            </el-form-item>
            <el-form-item label="views">
              <span>{{ props.row.views }}</span>
            </el-form-item>

          </el-form>
        </template>
      </el-table-column>

      <el-table-column label="Avatar">
        <template v-slot="scope">
          <el-avatar shape="circle" :size="60" fit="contain" :src="scope.row.avatar" @error="errorHandler">
            <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
          </el-avatar>
        </template>
      </el-table-column>
      <el-table-column
          label="Nickname"
          prop="nickname" sortable>
      </el-table-column>
      <el-table-column label="Creat time" width="170">
        <template v-slot="scope">{{ scope.row.createTime}}</template>
      </el-table-column>

      <el-table-column
          label="Operate">
        <template v-slot="scope">
          <el-button icon="el-icon-edit" type="success" size="small" @click="showEditDialog(scope.row)" plain></el-button>
          <el-popconfirm icon="el-icon-delete" iconColor="red" confirm-button-text="yes" cancel-button-text="no" title="Are you sure to delete？" @confirm="deleteFriendById(scope.row.id)">
            <el-button slot="reference" icon="el-icon-delete" size="small" type="danger" plain></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <p></p>
<!-- pagination-->
    <div style="text-align: center">
      <el-form inline>
        <el-form-item>
          <el-pagination @size-change="handleSizeChange"  @current-change="handleCurrentChange" :current-page="queryInfo.currentPage"
                         :page-sizes="[10, 20, 30, 50]" :page-size="queryInfo.pageSize" :total="total"
                         layout="total, sizes, prev, pager, next, jumper" background>
          </el-pagination>
        </el-form-item>
<!--        <el-form-item style="margin-left: 170px" >-->
<!--          <el-switch v-model="infoForm.commentEnabled" active-text="page comments" @change="commentEnabledChanged"></el-switch>-->
<!--        </el-form-item>-->
      </el-form>
    </div>




    <el-dialog title="Adding useful blog" width="40%" :visible.sync="addDialogVisible" :close-on-click-modal="false" @close="addDialogClosed" >
      <!--content-->
      <el-form :model="addForm" :rules="formRules" ref="addFormRef" label-width="80px">
        <el-form-item label="nickename" prop="nickname" label-width="150px">
          <el-input v-model="addForm.nickname"></el-input>
        </el-form-item>
        <el-form-item label="description" prop="description" label-width="150px">
          <el-input v-model="addForm.description"></el-input>
        </el-form-item>
        <el-form-item label="website" prop="website" label-width="150px">
          <el-input v-model="addForm.website"></el-input>
        </el-form-item>
        <el-form-item label="headURL" prop="avatar" label-width="150px">
          <el-input v-model="addForm.avatar"></el-input>
        </el-form-item>

      </el-form>
      <!--footer-->
      <span slot="footer">
				<el-button @click="addDialogVisible=false">cancel</el-button>
				<el-button type="primary" @click="saveFriend">determine</el-button>
			</span>
    </el-dialog>


    <el-dialog title="Editing sharing chain" width="40%" :visible.sync="editDialogVisible" :close-on-click-modal="false" @close="editDialogClosed">
      <!--content-->
      <el-form :model="editForm" :rules="formRules" ref="editFormRef">
        <el-form-item label="nick" prop="nickname" label-width="150px">
          <el-input v-model="editForm.nickname"></el-input>
        </el-form-item>
        <el-form-item label="description" prop="description" label-width="150px">
          <el-input v-model="editForm.description"></el-input>
        </el-form-item>
        <el-form-item label="website" prop="website" label-width="150px">
          <el-input v-model="editForm.website"></el-input>
        </el-form-item>
        <el-form-item label="headURL" prop="avatar" label-width="150px">
          <el-input v-model="editForm.avatar"></el-input>
        </el-form-item>

      </el-form>
      <!--footer-->
      <span slot="footer">
				<el-button @click="editDialogVisible=false">cancel</el-button>
				<el-button type="primary" @click="editFriend">determine</el-button>
			</span>
    </el-dialog>
  </div>

</template>

<script>
export default {
  name: "FriendList",
  data() {
    return {
      infoForm: {
        content: '',
        commentEnabled: true,
      },
      queryInfo: {
        currentPage: 1,
        pageSize: 10
      },
      friendList: [],
      total: 0,
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {
        nickname: '',
        description: '',
        website: '',
        avatar: '',
        isPublished: true
      },
      editForm: {
        nickname: '',
        description: '',
        website: '',
        avatar: '',
        isPublished: true
      },
      formRules: {
        nickname: [{required: true, message: 'please input nickname', trigger: 'blur'}],
        description: [{required: true, message: 'please input description', trigger: 'blur'}],
        website: [{required: true, message: 'please input website', trigger: 'blur'}],
        avatar: [{required: true, message: 'please input headURL', trigger: 'blur'}],
      }
    }
  },
  methods:{

    getFriendList() {
      const _this = this
      this.$axios.get('/friendList?currentPage=' + this.queryInfo.currentPage+"&pageSize=" + this.queryInfo.pageSize).then((res) => {
        _this.friendList = res.data.data.records
        _this.total = res.data.data.total

      });
    },

    handleCurrentChange(newPage) {
      this.queryInfo.currentPage = newPage
      this.getFriendList()
    },
    handleSizeChange(newPageSize){
      this.queryInfo.pageSize = newPageSize
      this.getFriendList()

    },
    showEditDialog(row){
      this.editForm = row
      this.editDialogVisible = true
    },
    editDialogClosed() {
      this.editForm = {}
      this.editDialogVisible = false
    },

    deleteFriendById(id) {
      const _this = this
      this.$axios.get('/friend/delete/' + id).then((res) => {
        _this.$alert('Operation Successfully', 'Notification', {
          confirmButtonText: 'Confirm',
          callback: action => {
            _this.getFriendList()

          }
        })
      })
    },


    saveFriend(){
      const _this = this
      this.$axios.post('/friend/create',this.addForm).then((res) => {
        _this.$alert('Operation Successfully', 'Notification', {
          confirmButtonText: 'Confirm',
          callback: action => {
            _this.addDialogVisible = false

            _this.getFriendList()

          }
        })
      });
      this.$refs['addFormRef'].resetFields();
    },
    editFriend(){
        const _this = this
        this.$axios.post('/friend/update', this.editForm).then(res => {
          _this.$alert('Operation Successfully', 'Notification', {
            confirmButtonText: 'Confirm',
            callback: action => {
              _this.editDialogVisible = false
              _this.getFriendList()
            }
          });
        });

    },
    commentEnabledChanged(){

    },
    addDialogClosed(){
      this.addDialogVisible = false
    },

  },
  created() {
    this.getFriendList()
  },
  errorHandler() {
    return true
  }
}


</script>

<style scoped>

.el-button + span {

  margin-right: 5px;
}

.el-form {
  margin-top: 15px !important;
}

.el-form--inline .el-form-item {

  margin-bottom: 0;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}
.demo-table-expand {

  font-size:0;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}

.demo-table-expand label {

  width: 90px;
  color: #a3bf99;
}
.demo-table-expand .el-form-item {

  margin-right: 10px;
  margin-bottom:10px;
  width: 50%;
}


</style>
