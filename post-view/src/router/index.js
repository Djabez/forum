import Vue from 'vue'
import VueRouter from 'vue-router'

import Admin from "../admin/Admin";

import Blog from "../admin/Blog";
import BlogEdit from '../admin/BlogEdit.vue';
import BlogList from "../admin/BlogList";
import FriendList from "@/admin/FriendList";
import CategoryList from "@/admin/CategoryList";
import Homepage from "@/admin/Homepage";
import BlogDetail from "@/admin/BlogDetail";

import login1 from "@/admin/login1";
import sharing from "@/admin/sharing";

Vue.use(VueRouter)
const routes = [

    {
        path: '/',
        name: 'admin',
        component: Admin,
        meta: {
            title: 'Admin'
        }
    },


    {
        path: '/login',
        name: 'Login',
        component: login1,
        meta: {
            title: 'Log in'
        }
    },



    {
        path: '/admin',
        name: 'Admin',
        component: Admin,
        meta: {
            title: 'Homepage',
            requireAuth: true
        },

        children: [

            {
                path: '/HomePage',
                name: 'HomePage',
                component: Homepage,
                meta: {
                    title: 'HomePage'
                }
            },


            {
                path: '/writePosts',
                name: 'BlogWrite',
                component: BlogEdit,
                meta: {
                    requireAuth: true,
                    title: 'writing'
                }
            },

            {
                path: '/postList',
                name: 'postList',
                component: BlogList,
                meta: {
                    requireAuth: true,
                    title: 'Post list'
                }
            },
            {
                path: '/blog/edit/:blogId',
                name: 'BlogEdit',
                meta: {
                    requireAuth: true,
                    title: 'Edit Post'
                },
                component: BlogEdit
            },
            {
                path: '/type',
                component: CategoryList,
                meta: {
                    requireAuth: true,
                    title: 'Classification list'
                }
            },

            {
                path: '/friendList',
                component: FriendList,
                meta: {
                    requireAuth: true,
                    title: 'Blog sharing'
                }
            },

            {
                path: '/blog',
                name: 'Post',
                component: Blog,
                meta: {
                    title: 'Post'
                }
            },
            {
                path: '/blog/:blogId',
                name: 'PostDetail',
                component: BlogDetail,
                meta: {

                    title: 'PostDetail'
                }
            },
            {
                path: '/sharing',
                name: 'Sharing',
                component: sharing,
                meta: {
                    title: 'Sharing'
                }
            },

        ]

    },



];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
})

export default router

