(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d2290a0"],{dc6a:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{ref:"searchForm",attrs:{inline:!0,model:e.queryOptions},nativeOn:{submit:function(t){return t.preventDefault(),e.handleFilter(t)}}},[a("el-form-item",{attrs:{prop:"course"}},[a("el-input",{attrs:{placeholder:"请输入课程名称"},model:{value:e.queryOptions.course,callback:function(t){e.$set(e.queryOptions,"course",t)},expression:"queryOptions.course"}})],1),a("el-form-item",[a("el-button",{attrs:{icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v("\n        搜索\n      ")]),a("el-button",{attrs:{type:"primary",icon:"el-icon-plus"},on:{click:e.handleCreate}},[e._v("\n        添加选课\n      ")])],1),a("el-button",{attrs:{type:"primary",plain:""},on:{click:function(t){e.showCheckbox=!e.showCheckbox}}},[e._v("\n      多选\n    ")]),e.showCheckbox?a("el-button",{attrs:{type:"danger"},on:{click:e.handleBatchDelete}},[e._v("\n      批量删除\n    ")]):e._e(),a("el-button",{on:{click:function(t){e.showExcelDialog=!0}}},[e._v("\n      批量导入选课\n    ")])],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"100%"},attrs:{data:e.data,border:"",fit:"","highlight-current-row":""},on:{"selection-change":e.handleSelect}},[e.showCheckbox?a("el-table-column",{attrs:{type:"selection",width:"55"}}):e._e(),a("el-table-column",{attrs:{align:"center",label:"学生编号",prop:"sno"}}),a("el-table-column",{attrs:{align:"center",label:"学生姓名",prop:"student"}}),a("el-table-column",{attrs:{align:"center",label:"课程序号",prop:"no"}}),a("el-table-column",{attrs:{align:"center",label:"课程名称",prop:"course"}}),a("el-table-column",{attrs:{align:"center",label:"老师名称",prop:"teacher"}}),a("el-table-column",{attrs:{align:"center",label:"学期",prop:"semester"}}),a("el-table-column",{attrs:{align:"center",label:"开始周",prop:"startWeek"}}),a("el-table-column",{attrs:{align:"center",label:"结束周",prop:"endWeek"}}),a("el-table-column",{attrs:{align:"center",prop:"createTime",label:"添加时间"}}),a("el-table-column",{attrs:{align:"center",prop:"updateTime",label:"更新时间"}}),a("el-table-column",{attrs:{align:"center",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini"},on:{click:function(a){return e.handleEdit(t.row)}}},[e._v("\n          编辑\n        ")]),a("el-button",{attrs:{type:"danger",size:"mini"},on:{click:function(a){return e.handleDelete(t.row)}}},[e._v("\n          删除\n        ")])]}}])})],1),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total > 0"}],attrs:{total:e.total,page:e.queryOptions.page,limit:e.queryOptions.pageSize},on:{"update:page":function(t){return e.$set(e.queryOptions,"page",t)},"update:limit":function(t){return e.$set(e.queryOptions,"pageSize",t)},pagination:e.requestData}}),a("el-dialog",{attrs:{title:(e.selectCourse.id?"编辑":"添加")+"支撑课程",visible:e.showDialog},on:{"update:visible":function(t){e.showDialog=t},close:function(t){e.showDialog=!1}}},[e.showDialog?a("el-form",{ref:"editForm",attrs:{model:e.editForm,rules:e.rules,"label-position":"top"}},[a("el-form-item",{attrs:{prop:"studentId",label:"学生"}},[a("el-select",{attrs:{filterable:"",remote:"","reserve-keyword":"",placeholder:"请选择学生","remote-method":e.queryStudentList,loading:e.loading},model:{value:e.editForm.studentId,callback:function(t){e.$set(e.editForm,"studentId",t)},expression:"editForm.studentId"}},e._l(e.studentList,function(e){return a("el-option",{key:e.id,attrs:{label:e.sno+" "+e.name,value:e.id}})}),1)],1),a("el-form-item",{attrs:{prop:"courseClassId",label:"课程班级"}},[a("el-select",{attrs:{filterable:"",remote:"","reserve-keyword":"",placeholder:"请选择指标点编号","remote-method":e.queryCourseClassList,loading:e.loading},model:{value:e.editForm.courseClassId,callback:function(t){e.$set(e.editForm,"courseClassId",t)},expression:"editForm.courseClassId"}},e._l(e.courseClassList,function(e){return a("el-option",{key:e.id,attrs:{label:e.no,value:e.id}})}),1)],1)],1):e._e(),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.showDialog=!1}}},[e._v("\n        取 消\n      ")]),a("el-button",{attrs:{type:"primary"},on:{click:e.handleSave}},[e._v("\n        确 定\n      ")])],1)],1),a("excel-dialog",{attrs:{action:"/select-course/batchSave",show:e.showExcelDialog},on:{"update:show":function(t){e.showExcelDialog=t},requestData:e.requestData}})],1)},n=[],s=(a("96cf"),a("3b8d")),i=a("d225"),o=a("b0b4"),l=a("308d"),u=a("6bb5"),c=a("4e2b"),d=a("9ab4"),p=a("60a3"),h=function(e){function t(){var e;return Object(i["a"])(this,t),e=Object(l["a"])(this,Object(u["a"])(t).apply(this,arguments)),e.data=[],e.total=0,e.loading=!0,e.selectCourse={},e.showDialog=!1,e.editForm={},e.showCheckbox=!1,e.selectCourseId=[],e.showExcelDialog=!1,e.studentList=[],e.courseClassList=[],e.queryOptions={no:"",course:"",student:"",teacher:"",semester:"",page:1,pageSize:20},e.rules={courseClassId:[{required:!0,message:"课程班级不能为空",trigger:"blur"}],studentId:[{required:!0,message:"学生不能为空",trigger:"blur"}]},e}return Object(c["a"])(t,e),Object(o["a"])(t,[{key:"created",value:function(){this.init()}},{key:"init",value:function(){var e=Object(s["a"])(regeneratorRuntime.mark(function e(){return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:this.requestData(),this.queryStudentList(""),this.queryCourseClassList("");case 3:case"end":return e.stop()}},e,this)}));function t(){return e.apply(this,arguments)}return t}()},{key:"handleFilter",value:function(){this.queryOptions.page=1,this.requestData()}},{key:"requestData",value:function(){var e=Object(s["a"])(regeneratorRuntime.mark(function e(){var t;return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return this.loading=!0,e.next=3,this.api.querySelectCourse(this.queryOptions);case 3:t=e.sent,this.data=t.data.list,this.total=t.data.total,this.loading=!1;case 7:case"end":return e.stop()}},e,this)}));function t(){return e.apply(this,arguments)}return t}()},{key:"handleCreate",value:function(){this.selectCourse={},this.resetForm(),this.showDialog=!0}},{key:"resetForm",value:function(){this.editForm={courseClassId:void 0,studentId:void 0}}},{key:"handleEdit",value:function(e){this.selectCourse=e,this.editForm={studentId:e.studentId,courseClassId:e.courseClassId},this.showDialog=!0}},{key:"handleSave",value:function(){var e=this;this.$refs["editForm"].validate(function(){var t=Object(s["a"])(regeneratorRuntime.mark(function t(a){var r,n;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:if(!a){t.next=14;break}if(!e.selectCourse.id){t.next=8;break}return t.next=4,e.api.putSelectCourse(e.selectCourse.id,e.editForm);case 4:r=t.sent,0===r.status&&(e.resetForm(),e.showDialog=!1,e.$message({type:"success",message:"修改成功!"}),e.requestData()),t.next=12;break;case 8:return t.next=10,e.api.createSelectCourse(e.editForm);case 10:n=t.sent,0===n.status&&(e.$refs["editForm"].resetFields(),e.showDialog=!1,e.$message({type:"success",message:"添加成功!"}),e.requestData());case 12:t.next=15;break;case 14:return t.abrupt("return",!1);case 15:case"end":return t.stop()}},t)}));return function(e){return t.apply(this,arguments)}}())}},{key:"handleDelete",value:function(e){var t=this;this.$confirm("确定删除这条信息吗？","提示",{type:"warning"}).then(Object(s["a"])(regeneratorRuntime.mark(function a(){var r;return regeneratorRuntime.wrap(function(a){while(1)switch(a.prev=a.next){case 0:return a.next=2,t.api.deleteSelectCourse(e.id);case 2:r=a.sent,0===r.status&&(t.$message({type:"success",message:"删除成功!"}),t.data=t.data.filter(function(t){return t.id!==e.id}),t.total>1?t.total--:t.requestData());case 4:case"end":return a.stop()}},a)})))}},{key:"queryStudentList",value:function(){var e=Object(s["a"])(regeneratorRuntime.mark(function e(t){var a,r;return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return a={page:1,pageSize:20,name:t},e.next=3,this.api.queryStudent(a);case 3:r=e.sent,0===r.status&&(this.studentList=r.data.list);case 5:case"end":return e.stop()}},e,this)}));function t(t){return e.apply(this,arguments)}return t}()},{key:"queryCourseClassList",value:function(){var e=Object(s["a"])(regeneratorRuntime.mark(function e(t){var a,r;return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return a={page:1,pageSize:20,no:t},e.next=3,this.api.queryCourseClass(a);case 3:r=e.sent,0===r.status&&(this.courseClassList=r.data.list);case 5:case"end":return e.stop()}},e,this)}));function t(t){return e.apply(this,arguments)}return t}()},{key:"handleSelect",value:function(e){this.selectCourseId=e.map(function(e){return e.id})}},{key:"handleBatchDelete",value:function(){var e=this;this.selectCourseId.length<1?this.$message({type:"warning",message:"请先选择要删除项"}):this.$confirm("确定要批量删除所选项吗？","提示",{type:"warning"}).then(Object(s["a"])(regeneratorRuntime.mark(function t(){var a;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,e.api.batchDeleteSelectCourse(e.selectCourseId);case 2:a=t.sent,0===a.status&&(e.$message({type:"success",message:"删除成功!"}),e.requestData());case 4:case"end":return t.stop()}},t)})))}}]),t}(p["d"]);h=Object(d["a"])([Object(p["a"])({})],h);var m=h,g=m,b=a("2877"),f=Object(b["a"])(g,r,n,!1,null,null,null);t["default"]=f.exports}}]);
//# sourceMappingURL=chunk-2d2290a0.7166e52d.js.map