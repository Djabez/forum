<template>
  <div>

    <!--Adding-->
    <el-form inline>
      <el-form-item>
        <el-button type="primary" size="small" icon="el-icon-plus" @click="addDialogVisible=true">Add classification</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="typeList">
      <el-table-column label="Sequence" type="index" width="300"></el-table-column>
      <el-table-column label="Name" prop="typeName"></el-table-column>

      <el-table-column label="Operation" width="500">
        <template v-slot="scope">
          <div v-if="ifShow">
          <el-button icon="el-icon-edit" size="mini" type="primary" @click="showEditDialog(scope.row)">edit</el-button>

          <el-popconfirm icon="el-icon-delete" iconColor="red" title="Do you want to delete？" @confirm="deleteTypeById(scope.row.id)">

            <el-button slot="reference" icon="el-icon-delete" size="mini" type="danger">delete</el-button>

          </el-popconfirm>
          </div>
        </template>
      </el-table-column>

    </el-table>

    <!--Pagination-->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.currentPage"
                   :page-sizes="[10, 20, 30, 50]" :page-size="queryInfo.pageSize" :total="total"
                   layout="total, sizes, prev, pager, next, jumper" background>
    </el-pagination>


    <!--Add the classification-->
    <el-dialog title="Add classification" width="40%" :visible.sync="addDialogVisible" :close-on-click-modal="false" @close="addDialogClosed">

      <el-form :model="addForm" :rules="formRules" ref="addFormRef" label-width="80px">
        <el-form-item label="Name" prop="typeName">
          <el-input v-model="addForm.typeName"></el-input>
        </el-form-item>
      </el-form>
      <!--Footer-->
      <span slot="footer">
				<el-button @click="addDialogVisible=false">Cancel</el-button>
				<el-button type="primary" @click="saveType">Confirm</el-button>
			</span>
    </el-dialog>

    <!--edit part-->
    <el-dialog title="edit Classification" width="40%" :visible.sync="editDialogVisible" :close-on-click-modal="false" @close="editDialogClosed">
      <!--main part-->
      <el-form :model="editForm" :rules="formRules" ref="editFormRef" label-width="80px">
        <el-form-item label="Name" prop="typeName">
          <el-input v-model="editForm.typeName"></el-input>
        </el-form-item>
      </el-form>
      <!--footer-->
      <span slot="footer">
				<el-button @click="editDialogVisible=false">Cancel</el-button>
				<el-button type="primary" @click="editType">Confirm</el-button>
			</span>
    </el-dialog>
  </div>

</template>

<script>
export default {
  name: "Category",
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

      ifShow:false,

      typeList: [],
      total: 0,
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {
        typeName: '',

      },
      editForm: {
        typeName: '',
      },
      formRules: {
        typeName: [{required: true, message: 'Please provide the name', trigger: 'blur'},
          {min: 1, max:12, message: 'The length should be 1 to 12', trigger: 'blur'}],

      }
    }
  },
  methods:{

    getTypeList() {
      const _this = this
      this.$axios.get('/type/list?currentPage=' + this.queryInfo.currentPage+"&pageSize=" + this.queryInfo.pageSize).then((res) => {
        _this.typeList = res.data.data.records
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

    deleteTypeById(id) {
      const _this = this
      this.$axios.get('/type/delete/' + id).then((res) => {
        _this.$alert('Operation successfully', 'Notification', {
          confirmButtonText: 'Confirm',
          callback: action => {
            _this.getTypeList()

          }
        })
      })
    },


    saveType(){
      const _this = this
      this.$axios.post('/type/create',this.addForm).then((res) => {
        _this.$alert('Operation successfully', 'Notification', {
          confirmButtonText: 'Confirm',
          callback: action => {
            _this.addDialogVisible = false

            _this.getTypeList()

          }
        })
      });
      this.$refs['addFormRef'].resetFields();
    },
    editType(){
      const _this = this
      this.$axios.post('/type/update', this.editForm).then(res => {
        _this.$alert('Operation successfully', 'Notification', {
          confirmButtonText: 'Confirm',
          callback: action => {
            _this.editDialogVisible = false
            _this.getTypeList()
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
    this.getTypeList()
    if(this.$store.getters.getUser.username){
      if(this.$store.getters.getUser.role=="role_root"){
        this.ifShow = true
        this.getTypeList()

      }
      else {
        this.$message.error("you don't have authority")
      }
    }
    else {
      this.$message.error("you don't have authority")
    }

  }
}


</script>

<style scoped>
.el-button + span {
  margin-left: 10px;
}


.el-form--inline .el-form-item {
  margin-bottom: 0;
}


</style>
