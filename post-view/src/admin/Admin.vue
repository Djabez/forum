<template>
  <el-container class="admin-container" >

    <!--Header-->
    <el-header>
      <!--Title-->
      <div>
        <img alt="" height="60" src="../assets/logo.png">
        <span> MingYu Forum  </span>
        <span style="margin-left: 20px"> Username:{{ user.username }}</span>
        <el-divider direction="vertical"></el-divider>
        <span><el-link href="/HomePage" style="color: white" >Homepage</el-link></span>
        <Header></Header>
      </div>
      <!-- Avatar -->
      <el-dropdown v-if="user" class="user" trigger="click" @command="logout">
        <div class="el-dropdown-link">
          <el-avatar :size="45" :src="user.avatar" fit="contain" shape="circle"></el-avatar>
        </div>
        <!--Dropdown below the avatar-->
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item icon="ali-iconfont icon-logout">Log in</el-dropdown-item>
          <el-dropdown-item icon="ali-iconfont icon-logout">Log out</el-dropdown-item>
        </el-dropdown-menu>


      </el-dropdown>
    </el-header>

    <!--Main page-->
    <el-container>

      <!--Navigation bar-->
      <el-aside :width="isCollapse? '64px' : '190px'">
        <div class="toggle-button" @click="isCollapse=!isCollapse"><i
            :class="isCollapse?'el-icon-s-unfold':'el-icon-s-fold'"></i></div>
        <!--Menu-->
        <el-menu :collapse="isCollapse" :collapse-transition="false" :default-active="$store.state.activePath"
                 :default-openeds="defaultOpeneds"
                 :router="true" :unique-opened="false" active-text-color="#409eff"
                 background-color="honeydew" text-color="black">
<!--          <el-menu-item index="/dashboard">-->
<!--            <i class="iconfont ali-iconfont icon-dashboard"></i>-->
<!--            <span>仪表盘</span>-->
<!--          </el-menu-item>-->

          <!-- first level menu -->
          <el-submenu v-for="item in menuList" :key="item.id" :index="item.id + ''">
            <!-- The template area of the first level menu -->
            <template slot="title">
              <i :class="iconsObj[item.id]" class="iconfont"></i>
              <span>{{ item.title }}</span>
            </template>
            <!-- second level menu -->
            <el-menu-item v-for="subItem in item.children" :key="subItem.id" :index="subItem.path">
              <template slot="title">
                <i :class="iconsObj[subItem.id]"></i>
                <span>{{ subItem.title }}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <!--the right main part-->
      <el-main :class="isCollapse?'m-el-main-width-64':'m-el-main-width-190'" >

        <router-view :key="$route.fullPath"/>

      </el-main>

    </el-container>


  </el-container>

</template>

<script>
export default {
  name: "Admin",

  data() {
    return {
      focusMode:false,
      menuList: [
        {
          id: 54,
          title: 'Homepage',
          children: [
            {
              id: 54,
              title: 'Homepage',
              path: '/Homepage'
            },
        ]
        },

        {
          id: 1,
          title: 'Posts Management',
          children: [
            {
              id: 11,
              title: 'Writing Posts',
              path: '/writePosts'
            },
            {
              id: 13,
              title: 'Posts lists',
              path: '/postList'
            },
            {
              id: 15,
              title: 'Classification lists',
              path: '/type'
            },
            {
              id: 53,
              title: 'View Posts',
              path: '/blog'
            },
              ]
        },
        {
          id: 55,
          title: 'Useful tool',
          children: [

            {
              id: 22,
              title: 'Blog sharing',
              path: '/friendList'
            },
            {
              id: 56,
              title: 'Material sharing',
              path: '/sharing'
            },


          ]
        },

      ],
      iconsObj: {
        '1': 'el-icon-menu',
        '2': 'el-icon-document-copy',
        '3': 'el-icon-s-tools',
        '4': 'el-icon-document',
        '5': 'el-icon-s-data',
        '11': 'el-icon-edit',
        '12': 'el-icon-edit',
        '13': 'el-icon-s-order',
        '14': 'el-icon-chat-dot-round',
        '15': 'el-icon-s-opportunity',
        '16': 'submenu ali-iconfont icon-biaoqian',
        '17': 'el-icon-s-comment',
        '21': 'submenu ali-iconfont icon-bianjizhandian',
        '22': 'el-icon-share',
        '23': 'el-icon-tickets',
        '31': 'el-icon-user-solid',
        '32': 'el-icon-alarm-clock',
        '41': 'el-icon-alarm-clock',
        '42': 'el-icon-finished',
        '43': 'el-icon-document-checked',
        '44': 'el-icon-document-delete',
        '45': 'el-icon-data-line',
        '51': 'el-icon-s-marketing',
        '52': 'el-icon-view',
        '53': 'el-icon-tickets',
        '54':'el-icon-s-home',
        '55':'el-icon-s-cooperation',
        '56':'el-icon-connection',
      },
      //collapse or not
      isCollapse: false,
      //default opened menu
      defaultOpeneds: ['1','54','55'],
      user: {},
    }
  },
  methods: {
    //gain the avatar and username
    getUserInfo() {
      if (this.$store.getters.getUser.username) {
        this.user.username = this.$store.getters.getUser.username
        this.user.avatar = this.$store.getters.getUser.avatar
      } else {
        this.$router.push('/login')
      }
    },
    //log out
    logout() {
      const _this = this
      this.$axios.get('/logout').then((res) => {
        _this.$store.commit('REMOVE_INFO')
        _this.$router.push('/login')
      });

    }
  },
  created() {
    this.getUserInfo()
  },
}
</script>

<style scoped>

.el-header {
  background-color: cornflowerblue;
  display: flex;
  justify-content: space-between;
  padding-left: 10px;
  align-items: center;
  color: #ffffff;
  font-size: 22px;
  user-select: none;
}
.el-header div {
  display: flex;
  align-items: center;
}
.el-header .title span {
  margin-left: 15px;
}
.el-aside {
  background-color: honeydew;
  position: absolute;
  top: 60px;
  bottom: 0;
  user-select: none;
}
.el-main {

  background-color: white;
  position: absolute;
  top: 60px;
  bottom: 0;
  right: 0;
  overflow-y: auto;
  overflow-x: hidden;

}
.el-aside .el-menu {
  border-right: none;
}
.m-el-main-width-190 {
  width: calc(100% - 190px);
}
.el-dropdown-menu {
  margin: 7px 0 0 0 !important;
  padding: 0 !important;
  border: 0 !important;
}
.m-el-main-width-64 {
  width: calc(100% - 64px);
}
.admin-container {
  height: 100%;
  }
.toggle-button {
  background-color: honeydew;
  font-size: 20px;
  line-height: 40px;
  color: black;
  text-align: center;
  cursor: pointer;
}
.el-dropdown-link {
  outline-style: none !important;
  outline-color: unset !important;
  height: 100%;
  cursor: pointer;
}
.el-main::-webkit-scrollbar-track-piece {
  background-color: transparent;
}
.el-main::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px transparent;
  box-shadow: inset 0 0 6px transparent;
  background-color: transparent;
}
.el-main::-webkit-scrollbar-thumb {
  -webkit-box-shadow: inset 0 0 6px #48dbfb;
  box-shadow: inset 0 0 6px #48dbfb;
  background-color: #48dbfb;

}
.el-aside {
  -ms-overflow-style: none; /* IE10 */
  scrollbar-width: none; /* Firefox */
}
.el-aside::-webkit-scrollbar {
  display: none;
}
.el-main::-webkit-scrollbar {
  width: 8px;
  height: 5px;
}

.el-main >.index-main-column-right {
  margin-left: 75%;
  margin-top: 2%;
  padding: 0;

}




</style>
