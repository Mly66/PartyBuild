<template>
	 <el-button type="primary" :icon="CirclePlus" circle :disabled="role != '基层支部书记'" @click="addMes" style="position: absolute;right:10px;z-index: 10;"/>
  <el-table :data="tableData" style="width: 100%" >
    <el-table-column label="走访者" width="130">
		<template #default="scope">
		  <p v-show="modifyTable[scope.$index] == 1">{{scope.row.interviewer}}</p>
		   <el-input v-show="modifyTable[scope.$index] != 1" v-model="scope.row.interviewer"></el-input>
		</template>
	</el-table-column>
    <el-table-column label="党支部" width="200">
		<template #default="scope">
		  <p  v-show="modifyTable[scope.$index] == 1">{{scope.row.belongto}}</p>
		   <el-input v-show="modifyTable[scope.$index] != 1" v-model="scope.row.belongto" disabled></el-input>
		</template>
	</el-table-column>
    <el-table-column label="受访者" width="200">
		<template #default="scope">
		  <p  v-show="modifyTable[scope.$index] == 1">{{scope.row.interviewee}}</p>
		   <el-input v-show="modifyTable[scope.$index] != 1" v-model="scope.row.interviewee"></el-input>
		</template>
    </el-table-column>
    <el-table-column label="走访地址" width="120">
		<template #default="scope">
		  <p  v-show="modifyTable[scope.$index] == 1">{{scope.row.interviewlocation}}</p>
		  <el-input v-show="modifyTable[scope.$index] != 1" v-model="scope.row.interviewlocation"></el-input>
		</template>
		</el-table-column>
		<el-table-column label="走访内容" width="120">
			<template #default="scope">
			  <p  v-show="modifyTable[scope.$index] == 1">{{scope.row.context}}</p>
			  <el-input v-show="modifyTable[scope.$index] != 1" v-model="scope.row.context"></el-input>
			</template>
			</el-table-column>
			<el-table-column label="志愿内容" width="120">
				<template #default="scope">
				  <p  v-show="modifyTable[scope.$index] == 1">{{scope.row.volunteercontext}}</p>
				  <el-input v-show="modifyTable[scope.$index] != 1" v-model="scope.row.volunteercontext"></el-input>
				</template>
				</el-table-column>
				<el-table-column label="走访时间" width="200">
					<template #default="scope">
					  <p  v-show="modifyTable[scope.$index] == 1">{{scope.row.interviewtime}}</p>
					  <div class="block"  v-show="modifyTable[scope.$index] != 1">
					        <span class="demonstration">选择开始时间</span>
					        <el-date-picker
					          v-model="scope.row.interviewtime"
					          type="date"
					          placeholder="Pick a day"
					          :disabled-date="disabledDate"
					          :shortcuts="shortcuts"
					          :size="size"
							 
					        />
					      </div>
					</template>
					</el-table-column>
			
    <el-table-column fixed="right" label="操作" width="120">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="handleClick(scope,1)"
          v-show="modifyTable[scope.$index] == 1" :disabled="role != '基层支部书记'">编辑</el-button
        >
		<el-button link type="primary" size="small" @click="handleClick(scope,2)"
		  v-show="modifyTable[scope.$index] == 2" :disabled="role != '基层支部书记'">保存</el-button
		>
		<el-button link type="primary" size="small" @click="handleClick(scope,3)"
		  v-show="modifyTable[scope.$index] == 3" :disabled="role != '基层支部书记'">确认</el-button
		>
        <el-button link type="primary" size="small" @click="deleteClick(scope)" :disabled="role != '基层支部书记'">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
 <div style="position: absolute;bottom: 10px;left:45%">
  <el-pagination  v-model:current-page="changePage.currentPage" background  :total="changePage.total" @current-change="currentChange"  layout="prev, pager, next" />
 </div>
</template>

<script setup>
import {findInterviewList,addInterview,updateInterview,deleteInterview} from '../api/InterviewAPI.js'
import {frontTimeSolve} from '../Utils/TimeUtil.js'
import {onMounted,ref,reactive} from 'vue'
import { ElMessage } from 'element-plus'
import {
 CirclePlus
} from '@element-plus/icons-vue'
const size = ref<'default' | 'large' | 'small'>('default')
const changePage = reactive({
  currentPage: 1,
  total: 0,
});




const currentChange = (val) => {
	getList()
}


const shortcuts = [
  {
    text: 'Today',
    value: new Date(),
  },
  {
    text: 'Yesterday',
    value: () => {
      const date = new Date()
      date.setTime(date.getTime() - 3600 * 1000 * 24)
      return date
    },
  },
  {
    text: 'A week ago',
    value: () => {
      const date = new Date()
      date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
      return date
    },
  },
]

const disabledDate = (time) => {
  return time.getTime() > Date.now()
}
onMounted(()=>{
	getList()
	
})
const handleClick = (scope,index) => {
	if(index == 1){
		 modifyTable.value[scope.$index] = 2
	}else if(index == 2){
		scope.row.starttime = new Date(scope.row.starttime).toISOString()
		scope.row.endtime = new Date(scope.row.endtime).toISOString()
		updateInterview(scope.row).then(res=>{
			if(res.code == 200){
                 getList()
				ElMessage({
				   message: '添加成功',
				   type: 'success',
				 })
			}else{
				ElMessage({
				   message: '服务器错误',
				   type: 'error',
				 })
			}
		})
	}else{
		scope.row.starttime = new Date(scope.row.starttime).toISOString()
		scope.row.endtime = new Date(scope.row.endtime).toISOString()
		addInterview(scope.row).then(res=>{
			if(res.code == 200){
				getList()
				ElMessage({
				   message: res.data,
				   type: 'success',
				 })
			}else{
				ElMessage({
				   message: '服务器错误',
				   type: 'error',
				 })
			}
		})
	}
}

const deleteClick = (scope) => {
	if(modifyTable.value[scope.$index] == 3){
		modifyTable.value.splice(scope.$index,1)
		tableData.value.splice(scope.$index,1)
		ElMessage({
		   message: '删除成功',
		   type: 'success',
		 })
	}else{
         deleteInterview(scope.row).then(res=>{
         	if(res.code == 200){
				modifyTable.value.splice(scope.$index,1)
				tableData.value.splice(scope.$index,1)
				ElMessage({
				   message: res.data,
				   type: 'success',
				 })
			}else{
				ElMessage({
				   message: '服务器错误',
				   type: 'error',
				 })
			}
         })		
	}
        
	
}

const addMes = () => {
	modifyTable.value.push(3)
	tableData.value.push(JSON.parse(JSON.stringify(obj)))
	
}


const getList = () =>{
	findInterviewList({belongto:JSON.parse(localStorage.getItem('token')).belongto},{page:changePage.currentPage,size:10}).then(res=>{
		if(res.code == 200){
			res.data.forEach((item)=>{
				item.interviewtime = frontTimeSolve(item.interviewtime)
			})
			changePage.total = res.size
			tableData.value = res.data
		    modifyTable.value = []
			tableData.value.forEach(()=>{
				modifyTable.value.push(1)
			})
		}else{
			ElMessage({
			   message: '服务器错误',
			   type: 'error',
			 })
		}
	})
}

const obj = reactive({
		belongto:JSON.parse(localStorage.getItem('token')).belongto
	
	
})

const role = ref(JSON.parse(localStorage.getItem('token')).role)

const tableData = ref([])

const modifyTable = ref([])
</script>
