<template>
  <!-- comment input -->
  <div class="form">
    <h3>
      Comment
      <el-button v-show="this.realParentCommentId !== -1" class="m-small" size="mini" type="primary"
                 @click="toSendParentId()">Cancellation
      </el-button>
    </h3>
    <el-form ref="formRef" :model="commentForm" :rules="rules" size="small">
      <el-input v-model="commentForm.content" :class="'textarea'" :rows="5" :validate-event="false" maxlength="250"
                placeholder="Write some comment" show-word-limit type="textarea"></el-input>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-form-item prop="nickname">

            <el-input v-model="commentForm.nickname" v-popover:nicknamePopover :validate-event="false" placeholder="Nickname(optional)"
                      @blur="onInputBlur" >
              <i slot="prefix" class="el-input__icon el-icon-user"></i>
            </el-input>


          </el-form-item>
        </el-col>

        <el-col :offset="1" :span="5">
          <el-form-item>

            <el-button size="medium" type="primary"  v-throttle="[postForm,`click`,30000]"  @mouseenter.native="beforePost">Submit</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>


  </div>
</template>

<script>

export default {
  name: "CommentForm",
  props: {
    realParentCommentId: {
      type: Number,
      default: '-1'
    },
    realParentCommentNickname: {
      type: String,
      default: ''
    }

  },
  data() {


    return {
      commentForm: {
        content: "",
        nickname: "",
        email: "DDjabez@gamil.com",
        website: "",
        avatar: "",
        blogId: 0,
        parentCommentId: this.realParentCommentId,
        isAdminComment: -1,
        qq: "",
        parentCommentNickname: this.realParentCommentNickname
      },

      rules: {
        nickname: [
          {required: false, message: 'please enter the nickname '},
          {min:2,max: 15, message: 'Nickname cannot larger than 15,less than 2', trigger: 'blur'}
        ],

      }

    }
  },
  methods: {


    //design hashcode

    hashFunc(str, size) {

      var hashCode = 0

      for (var i = 0; i < str.length; i++) {
        hashCode = 37 * hashCode + str.charCodeAt(i)
      }

      var index = hashCode % size + 1
      return index
    },

    toSendParentId() {
      this.$emit('parentEvent', 'Cancellation')

      this.commentForm.parentCommentId = -1
    },

    onInputBlur() {

      const _this = this


      if(this.$store.getters.getUser.nickname){
            this.commentForm.nickname = this.$store.getters.getUser.nickname
            this.commentForm.avatar = this.$store.getters.getUser.avatar
        }
      else {
        if(this.commentForm.nickname===""){
          this.commentForm.nickname="Anonymity"
        }

        //random avatar
        var randomNum = this.hashFunc(this.commentForm.nickname, 20)
        this.commentForm.avatar = "https://cdn.jsdelivr.net/gh/yubifeng/blog-resource/bloghosting/2021/avatar/avatar" + randomNum + ".webp"

      }
    },
    //check whether the avatar will be null
    beforePost() {
      if (this.commentForm.avatar == "") {
        this.onInputBlur()
      }

    },

    //submit the comment
    postForm() {

      this.$refs.formRef.validate((valid) => {
        if (valid) {
          console.log(JSON.stringify(this.commentForm))
          //judge whether the poster is owner or administrator
          if (this.commentForm.isAdminComment == 1) {
            this.commentForm.avatar = "https://cdn.jsdelivr.net/gh/yubifeng/blog-resource/bloghosting//website/static/websiteAvatar.webp"
          }
          const _this = this
          this.$axios.post('/comment/add', this.commentForm).then(res => {

            if (res.data.code == 200) {
              _this.$alert('Operate successfully', 'Notification', {
                confirmButtonText: 'Confirm',
                callback: action => {

                  location.reload()
                }
              });
            } else {
            }
          })

        } else {
          console.log('error submit!!');
          alert('There is something wrong');
          return false;
        }
      });
    }
  },
  watch: {

    realParentCommentId: function (newVal) {
      this.commentForm.parentCommentId = newVal
      console.log(JSON.stringify(this.commentForm))
    }
  },

  created() {
    if (this.$route.params.blogId) {
      this.commentForm.blogId = this.$route.params.blogId
    } else if (this.$route.path == "/about") {
      this.commentForm.blogId = 1
    } else if (this.$route.path == "/friends") {
      this.commentForm.blogId = 11
    } else {
      alert("error")
      return false
    }
    if (this.$store.getters.getUser) {
      if(this.$store.getters.getUser.role==="role_admin"||this.$store.getters.getUser.role==="role_root") {
        this.commentForm.isAdminComment = 1;
      }
    }
  },
}
</script>

<style scoped>
.form h3 {
  margin: 5px;
  font-weight: 500 !important;
}
.form .m-small {
  margin-left: 5px;
  padding: 4px 5px;
}
.el-form .textarea {
  margin-top: 5px;
  margin-bottom: 15px;
}
.el-form textarea {
  padding: 6px 8px;
}
.el-form textarea, .el-form input {
  color: black;
}
.el-form .el-form-item__label {
  padding-right: 3px;
}
</style>
