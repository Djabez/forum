<template>
  <div>
    <!--main content-->
    <div class="m-content">
      <!--the content of the edit interface-->
      <el-form ref="ruleForm" :model="ruleForm":rules="rules" class="demo-ruleForm" label-position="top"
               label-width="100px">
        <!--The title and the first picture's URL-->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="Title" prop="title">
              <el-input v-model="ruleForm.title" placeholder="please enter the title"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="The URL of main picture" prop="firstPicture">
              <el-input v-model="ruleForm.firstPicture" placeholder="The main picture which can be empty "></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <!--Description of the content -->
        <el-form-item label="Description" prop="description">
          <mavon-editor @dblclick.native="autoWrite" v-model="ruleForm.description" placeholder="Start to write"/>
        </el-form-item>
        <el-form-item label="Content" prop="content">
          <mavon-editor  v-model="ruleForm.content" placeholder="Start to write"></mavon-editor>
        </el-form-item>
        <!--Classification-->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="Category" prop="cate">
              <el-select v-model="ruleForm.typeId" :allow-create="true" :filterable="true" placeholder="Please choose the category（can add new category）" style="width: 100%;">
                <el-option v-for="type in types" :key="type.id" :label="type.typeName" :value="type.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <!--Word count-->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="word count" prop="words">
              <!-- double click will show the number of words-->
              <el-input v-model="ruleForm.words" placeholder="double click will show the count of words" type="number" @dblclick.native="computeWords"></el-input>
            </el-form-item>
          </el-col>

        </el-row>
        <!--submit and reset button-->
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">create</el-button>
          <el-button @click="resetForm('ruleForm')">reset</el-button>
        </el-form-item>


      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "BlogEdit",
  data() {
    return {
      types: {},
      ruleForm: {
        id: '',
        title: '',
        description: '',
        content: '',
        firstPicture: '',
        typeId: '',
        createTime: '2022-03-22 22:05:49',
        updateTime: '2022-03-22 22:05:49',
        userId: 1,
        words: null,
        views: 0,
        status: 1,

      },
      rules: {
        title: [
          {required: true, message: 'please enter the title', trigger: 'blur'},
          {min: 2, max: 45, message: 'the length should be 2 to 45', trigger: 'blur'}
        ],
        description: [
          {required: true, message: 'please enter the abstract', trigger: 'blur'}
        ],
        content: [
          {required: true, message: 'please enter the content', trigger: 'blur'}
        ],
        words: [{required: true, message: 'word count', trigger: 'change'}],
      }
    }
  },
  methods: {
    //after double click, add the first 100 words into the description
    autoWrite() {
      if(this.ruleForm.content.length<100){
        this.ruleForm.description = this.ruleForm.content.substring(0,this.ruleForm.content.length)
      }
      else{
        this.ruleForm.description = this.ruleForm.content.substring(0,100)
      }
    },
    //count the words
    computeWords() {
      var contents = this.ruleForm.content
      this.ruleForm.words = contents.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, '').length
    },
    //submit the post
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          if(_this.ruleForm.id==""){
            this.$axios.post('/blog/create', this.ruleForm).then(res => {
              console.log(res)
              _this.$alert('Operation successfully', 'Notification', {
                confirmButtonText: 'Confirm',
                callback: action => {
                  _this.$router.push("/postList")
                }
              });
            })
          }
          else{
            this.$axios.post('/blog/update', this.ruleForm).then(res => {
              console.log(res)
              _this.$alert('Operation successfully', 'Notification', {
                confirmButtonText: 'Confirm',
                callback: action => {
                  _this.$router.push("/postList")
                }
              });
            })
          }

        }
        else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    //reset all the content
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //gain the classification
    getTypes() {
      const _this = this
      this.$axios.get('/types').then(res => {
        _this.types = res.data.data
      })
      console.log(this.types)
    },
    //gain the post
    getBlogById() {
      const blogId = this.$route.params.blogId
      const _this = this
      if (blogId) {
        this.$axios.get('/blog/detail/' + blogId).then(res => {
          const blog = res.data.data
          _this.ruleForm.id = blog.id
          _this.ruleForm.title = blog.title
          _this.ruleForm.firstPicture = blog.firstPicture
          _this.ruleForm.description = blog.description
          _this.ruleForm.content = blog.content
          _this.ruleForm.words = blog.words
          _this.ruleForm.views = blog.views
          _this.ruleForm.typeId = blog.typeId
          _this.ruleForm.status = blog.status
          _this.ruleForm.userId = blog.userId
          _this.ruleForm.createTime = blog.createTime
          _this.ruleForm.updateTime = blog.updateTime
        })
      }
    }
  },
  created() {
    this.getTypes();
    this.getBlogById();
  }
}
</script>
<style scoped>
.m-content {
  width: 100%;
}
</style>
