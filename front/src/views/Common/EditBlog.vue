<script setup>
import {reactive, ref} from 'vue';
import { MdEditor } from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import {post} from "@/net/index.js";
import {message} from "ant-design-vue";
const [messageApi, contextHolder] = message.useMessage();

const blogForm=reactive({
  title:'',
  content:''
})

const submitHandler = () => {
  post('/api/blog/addNew', {
    title: blogForm.title,
    content: blogForm.content
  }, (message) => {
    messageApi.success(message);
  }, (message) => {
    messageApi.warning(message)
  }, (message) => {
    messageApi.error(message)
  })
}

</script>

<template>
  <contextHolder/>
  <br>
 <div class="w-3/4 h-screen mx-auto">
   <div>
     <span>菜名:（制作步骤请写在下方内容框中）</span>
     <input v-model="blogForm.title" class="animatedInput w-full border-2">
   </div>
   <MdEditor v-model="blogForm.content" :toolbarsExclude="['github']"/>

<!--   <div class="left-10">-->
<!--     <br>-->
<!--     <a-form-->
<!--         :model="formState"-->
<!--         name="basic"-->
<!--         :label-col="{ span: 8 }"-->
<!--         :wrapper-col="{ span: 16 }"-->
<!--         autocomplete="off"-->
<!--         @finish="onFinish"-->
<!--         @finishFailed="onFinishFailed"-->
<!--     >-->
<!--       <a-form-item-->
<!--           label="名称"-->
<!--           name="username"-->
<!--           :rules="[{ required: true, message: '请输入你要发布的菜名' }]"-->
<!--       >-->
<!--         <a-input placeholder="名称" />-->
<!--       </a-form-item>-->
<!--       &lt;!&ndash;rules为提示语句&ndash;&gt;-->
<!--       <a-form-item label="分类"-->
<!--                    name="region"-->
<!--                    :rules="[{ required: true, message: '' }]"-->
<!--       >-->
<!--         <a-select  placeholder="请选择">-->
<!--           <a-select-option >炒饭</a-select-option>-->
<!--           <a-select-option >粥面</a-select-option>-->
<!--           <a-select-option >面食</a-select-option>-->
<!--           <a-select-option >红烧排骨</a-select-option>-->
<!--           <a-select-option >芹菜</a-select-option>-->
<!--           <a-select-option >沙拉</a-select-option>-->
<!--           <a-select-option >酸菜鱼</a-select-option>-->
<!--           <a-select-option >麻辣小龙虾</a-select-option>-->
<!--           <a-select-option >红烧肉</a-select-option>-->
<!--           <a-select-option >麻婆豆腐</a-select-option>-->
<!--         </a-select>-->
<!--       </a-form-item>-->

<!--       <a-form-item label="封面">-->
<!--         <a-button type="primary" html-type="submit">上传封面</a-button>-->
<!--       </a-form-item>-->

<!--       <a-form-item label="技巧" name="desc">-->
<!--         <a-textarea  />-->
<!--       </a-form-item>-->

<!--       <a-form-item label="食材" name="desc">-->
<!--         <a-textarea  />-->
<!--       </a-form-item>-->

<!--       <a-form-item label="步骤" name="desc">-->
<!--         <a-textarea  />-->
<!--       </a-form-item>-->

<!--       <a-form-item :wrapper-col="{ offset: 8, span: 16 }">-->
<!--         <a-button type="primary" html-type="submit">发布</a-button>-->
<!--       </a-form-item>-->
<!--     </a-form>-->
<!--   </div>-->

   <div class="w-full justify-center text-center mt-4">
     <button @click="submitHandler" class="bg-green-400  p-2 rounded-xl hover:bg-green-500 active:bg-green-700 font-bold">提交菜谱</button>
   </div>
 </div>


</template>


<style scoped>

</style>