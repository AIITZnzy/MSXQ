import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      name:'auth',
      path:'/auth',
      component:()=>import('@/views/Auth/Auth.vue'),
    },{
      name:'framework',
      path:'/',
      component:()=>import('@/views/Auth/Framework.vue'),
      children:[
        {
          name: 'index',
          path: '/',
          component: () => import('@/views/Common/Index.vue')
        },{
          name:'editBlog',
          path:'/new',
          component:()=>import('@/views/Common/Menu.vue')
        },{
          name:'viewBlog',
          path:'/view',
          component:()=>import('@/views/Common/viewBlog.vue')
        },{
          name:'myself',
          path:'/myself',
          component:()=>import('@/views/Common/Myself.vue')
        },{
          name:'notes',
          path:'notes',
          component:()=>import('@/views/Common/Notes.vue')
        },{
          name:'addnotes',
          path:'addnotes',
          component:()=>import('@/views/Common/EditBlog.vue')
        },{
          name:'ai',
          path:'ai',
          component:()=>import('@/views/Common/AI.vue')
        },{
          name:'food',
          path:'/food/:id',
          component:()=>import('@/views/Common/Food/food2.vue')
        }
      ]
    }
  ],
})

export default router
