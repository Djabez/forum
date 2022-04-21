<template>

  <div id="poster">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="login-container" label-position="left"
             label-width="30px">

      <div class="login-title">MingYu Forum</div>

      <div class="login-form">

        <el-form-item class="user-input">
          <el-input auto-complete="off" placeholder="Account" prefix-icon="el-icon-user"
                    type="text"
                    v-model="ruleForm.username"></el-input>
        </el-form-item>

        <el-form-item class="password-input">
          <el-input auto-complete="off" placeholder="Password" prefix-icon="el-icon-lock"
                    type="password"
                    v-model="ruleForm.password"></el-input>
        </el-form-item>

        <el-form-item>
          <el-row type="flex" justify="space-around">
            <el-button style="width: 160px;" round type="primary" @click="submitForm('ruleForm')">
              Login
            </el-button>

            <el-button style="width: 160px;" type="warning" round @click="resetForm('ruleForm')">
              Reset
            </el-button>
          </el-row>

          <el-row style="margin-top: 20px;" type="flex" justify="space-around">
            <el-button style="width: 160px;" round @click="addDialogVisible = true" type="info">
              Register
            </el-button>

            <el-button style="width: 160px;" round @click="changePasswordDialogVisible = true"
                       type="success">
              Change Password
            </el-button>
          </el-row>
        </el-form-item>
      </div>

    </el-form>

    <el-dialog title="Change Password" width="40%" :visible.sync="changePasswordDialogVisible"
               :close-on-click-modal="true"
               @close="changePasswordDialogClosed" :append-to-body="true">
      <el-form :model="changePasswordForm" :rules="changePassFormRules" ref="changePasswordRef"
               label-width="220px">
        <el-form-item label="email" prop="email" style="margin-top: 20px;">
          <el-input v-model="changePasswordForm.email" type="input"></el-input>
        </el-form-item>
        <el-form-item label="old password" prop="oldPassword" style="margin-top: 20px;">
          <el-input v-model="changePasswordForm.oldPassword" type="password"></el-input>
        </el-form-item>

        <el-form-item label="old password again" prop="oldPassword2" style="margin-top: 20px;">
          <el-input v-model="changePasswordForm.oldPassword2" type="password"></el-input>
        </el-form-item>

        <el-form-item label="new password" prop="newPassword" style="margin-top: 20px;">
          <el-input v-model="changePasswordForm.newPassword" type="password"></el-input>
        </el-form-item>


      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible=false">Cancel</el-button>
        <el-button type="primary" @click="changePassword1">Confirm</el-button>
      </div>


    </el-dialog>

    <el-dialog title="Registry" width="40%" :visible.sync="addDialogVisible" :close-on-click-modal="true"
               @close="addDialogClosed" :append-to-body="true">
      <!--content-->
      <el-form :model="addForm" :rules="formRules" ref="addFormRef" label-width="100px">

        <el-form-item label="Username" prop="username">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="Nickname" prop="nickname" style="margin-top: 20px;">
          <el-input v-model="addForm.nickname"></el-input>
        </el-form-item>
        <el-form-item label="password" prop="password" style="margin-top: 20px;">
          <el-input v-model="addForm.password" type="password"></el-input>
        </el-form-item>
        <el-form-item label="email" prop="email" style="margin-top: 20px;">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
        <el-form-item label="avatar URL" prop="avatar" style="margin-top: 20px;">
          <el-input v-model="addForm.avatar"></el-input>
        </el-form-item>

      </el-form>
      <!--footer-->
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible=false">Cancel</el-button>
        <el-button type="primary" @click="saveUser">Confirm</el-button>
      </div>
    </el-dialog>


  </div>


</template>

<script>
export default {
  name: 'Login',
  data() {

    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('please enter the password'));
      } else {
        callback();
      }
    };

    var validateChangePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('please enter the password'));
      } else {

        callback();
      }
    };
    var validateChangePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('please enter the password again'));
      } else if (value !== this.changePasswordForm.oldPassword) {
        callback(new Error('the two passwords do not match!'));
      } else {
        callback();
      }
    };

    var checkEmail = (rule, value, callback) => {
      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
      if (!value) {
        return callback(new Error('Please enter the email'))
      }
      setTimeout(() => {
        if (mailReg.test(value)) {
          callback()
        } else {
          callback(new Error('Please enter the correct email format'))
        }
      }, 100)
    };
    return {
      formLabelWidth: '150px',
      ruleForm: {
        password: '',
        username: ''
      },


      dialogFormVisible: false,
      changePasswordDialogVisible: false,

      rules: {
        password: [
          {validator: validatePass, trigger: 'blur'}
        ],
        username: [
          {required: true, message: 'please enter the username', trigger: 'blur'},
          {min: 3, max: 12, message: 'The length should be 3 to 12', trigger: 'blur'}
        ]
      },

      formRules: {
        nickname: [
          {required: true, message: 'Please enter the nickname', trigger: 'blur'}
        ],
        username: [
          {required: true, message: 'Please enter the username', trigger: 'blur'}
        ],
        password: [
          {required: true, message: 'Please enter password', trigger: 'blur'}
        ],
        avatar: [
          {required: true, message: 'Please enter avatar url', trigger: 'blur'}
        ],
        email: [
          {validator: checkEmail, trigger: 'blur'},
          {required: true, message: 'Please enter the email', trigger: 'blur'}
        ]
      },

      changePassFormRules: {

        oldPassword: [
          {required: true, validator: validateChangePass, trigger: 'blur'},
          {required: true, message: 'Please enter password', trigger: 'blur'}
        ],
        oldPassword2: [
          {required: true, validator: validateChangePass2, trigger: 'blur'},
          {required: true, message: 'Please enter password again', trigger: 'blur'}
        ],
        newPassword: [
          {required: true, message: 'Please enter password', trigger: 'blur'}
        ],
        email: [
          {required: true, validator: checkEmail, trigger: 'blur'},
          {required: true, message: 'Please enter the email', trigger: 'blur'}
        ]
      },

      infoForm: {
        content: '',
        commentEnabled: true,
      },

      changePasswordForm: {
        email: '',
        oldPassword: '',
        oldPassword2: '',
        newPassword: ''
      },

      ifShow: false,
      userList: [],
      total: 0,
      addDialogVisible: false,
      editDialogVisible: false,

      addForm: {
        nickname: '',
        description: '',
        website: '',
        avatar: '',
        status: 1,
        isPublished: true,
        role: 'role_user'
      },
      editForm: {
        nickname: '',
        description: '',
        website: '',
        avatar: '',
        isPublished: true
      },

    };
  },
  methods: {
    //login
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {

          this.$axios.post('/login', this.ruleForm).then((res) => {
            const token = res.headers['authorization']
            _this.$store.commit('SET_TOKEN', token)
            _this.$store.commit('SET_USERINFO', res.data.data)
            _this.$router.push("/Homepage")
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },


    //reset the username and password
    resetForm(formName) {
      this.ruleForm.username = '';
      this.ruleForm.password = '';
    },

    showEditDialog(row) {
      this.editForm = row
      this.editDialogVisible = true
    },
    editDialogClosed() {
      this.editForm = {}
      this.editDialogVisible = false
    },

    saveUser() {
      const _this = this
      this.$axios.post('/user/create1', this.addForm).then((res) => {
        _this.$alert('Operation successfully', 'Notification', {
          confirmButtonText: 'Confirm',
          callback: action => {
            _this.addDialogVisible = false
          }
        })
      });
      this.$refs['addFormRef'].resetFields();
    },

    changePassword1() {

      const _this = this
      this.$axios.post('/user/changepassword?email=' + this.changePasswordForm.email +
          '&oldpassword=' + this.changePasswordForm.oldPassword +
          '&oldpassword2=' + this.changePasswordForm.oldPassword2 +
          '&password=' + this.changePasswordForm.newPassword).then((res) => {
        _this.$alert('Operation successfully', 'Notification', {
          confirmButtonText: 'Confirm',
          callback: action => {
            _this.changePasswordDialogVisible = false
          }
        })
      });
      this.$refs['changePasswordRef'].resetFields();
    },

    commentEnabledChanged() {

    },

    addDialogClosed() {
      this.addDialogVisible = false
    },
    changePasswordDialogClosed() {
      this.changePasswordDialogVisible = false
    }

  },
  created() {

  },
  mounted() {
  }
}
</script>

<style scoped>
* {
  font-size: 16px;
  margin: 0;
  padding: 0;
}

#poster {
  background: url("../assets/login-background.jpg") no-repeat;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

a {
  text-decoration: none;
  color: #ffffff;
}

.login-title {
  width: 100%;
  height: 70px;
  font: 26px/1.4 "microsoft yahei";
  text-align: center;
  text-indent: 0;
  letter-spacing: 3px;
  color: rgba(244, 255, 253, 0.88);
  line-height: 70px;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
}

.login-container {
  background: rgba(246, 220, 206, 0.30);
  border-radius: 15px;
  background-clip: padding-box;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  width: 380px;
  height: 270px;

  padding-bottom: 40px;
  /*background: white;*/
}

.input-content /deep/ .el-input__inner {
  border: none;
  background: rgba(223, 225, 246, 0.3);
  color: black;
}

.empty {
  margin-bottom: 20px;
}

.form input {
  width: 370px;
  height: 40px;
  border-radius: 10px;
  /* float: right; */
  border: 1px solid #333333;
  outline: none;
}

.login-button {
  width: 50%;
  background-color: #0D1329;
  color: white;
  margin-left: 50px;
  border: none;
}

.user-input {
  margin-bottom: 20px;
  width: 90%;
}

.password-input {
  margin-bottom: 20px;
  width: 90%;
}

.form span {
  display: block;
  float: right;
}

.register-col {
  margin-bottom: 24px;
}
</style>
