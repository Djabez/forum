<template>
  <div class="blog-detail">
    <div class="mblog">

      <div class="home-title">
        <h2>{{ blog.title }}</h2>
        <span style="font-size: small;color: blue">Created time：{{ blog.createTime.split(' ')[0] }}  </span>
        <span style="font-size: small;color: blue">Updated time：{{ blog.updateTime.split(' ')[0] }}  </span>
        <span style="font-size: small;color: blue">Classification：{{ blog.typeName }}</span>
        <span style="font-size: small;color: blue"> Word count：{{ blog.words }}</span>

      </div>

      <el-link v-if="ownBlog" icon="el-icon-edit">
        <router-link :to="{name: 'BlogEdit', params: {blogId: blog.id}}">
          edit
        </router-link>
      </el-link>

      <markdown-it-vue-light class="md-body" :content="blog.content" v-viewer="{movable: false}" />

    </div>


    <div>
      <Comment></Comment>

    </div>

  </div>
</template>

<script>
import Comment from "@/components/Comment";
export default {
  name: "test",
  components: {Comment},
  data() {
    return {
      types: [],
      blog: {
        id: "",
        title: "",
        content: "",
        createTime: "",
        updateTime: "",
        typeName: "",
        words: 0,
        views: 0,
      },
      ownBlog: false
    }
  },
  methods: {
    getTypes() {
      const _this = this
      this.$axios.get('/types').then(res => {
        _this.types = res.data.data
      })

    },
    getBlog() {
      const blogId = this.$route.params.blogId
      const _this = this
      this.$axios.get('/blog/' + blogId).then(res => {
        const blog = res.data.data
        _this.blog.id = blog.id
        _this.blog.title = blog.title
        _this.blog.createTime = blog.createTime
        _this.blog.updateTime = blog.updateTime
        _this.blog.views = blog.views
        _this.blog.words = blog.words

        _this.blog.content = blog.content
        if (_this.$store.getters.getUser) {
          _this.ownBlog = (blog.userId === _this.$store.getters.getUser.id)
        } else {
          _this.ownBlog = false
        }
        for (var i in _this.types) {
          if (blog.typeId == _this.types[i].id) {
            _this.blog.typeName = _this.types[i].typeName
          }
        }
      })
    }
  },
  created() {
    this.getTypes()
    this.getBlog()
  }
}
</script>

<style scoped>
.mblog {
  min-height: 600px;
  padding: 5px 35px 5px 35px;
  text-align: center;
}
.home-title {
  margin-bottom: 40px;
}
.md-body {
  text-align: left;
}
.blog-detail {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  background-color: white;
}
.author-message {
  text-align: left;
  background-color: honeydew;
  padding: 10px 5px 10px 5px;
  font-size: 14px;
}
.el-divider {
  margin: 1rem 0 !important;
}
.code {
  background-color: #333333;
}
</style>
