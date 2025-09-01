<!-- eslint-disable no-mixed-spaces-and-tabs -->
<template>
     <div id="app">
		<div class="container">
        <div class="login-form" style="backgroundColor:rgba(183, 174, 174, 0.5);borderRadius:22px">
		<h1 class="h1" style="color:#000;fontSize:28px;">点餐系统商家入驻</h1>
		<!-- <el-form ref="rgsForm" class="rgs-form" :model="rgsForm" label-width="120px">
              
		</el-form> -->
		<el-form :model="rgsForm" :rules="rules" ref="rgsForm" label-width="120px" class="rgs-form">
			<el-form-item label="商家用户名" class="input" >
			  <el-input v-model="ruleForm.username" autocomplete="off" placeholder="用户名" type="text"></el-input>
			</el-form-item>
												 
			<el-form-item label="密码" class="input" >
			  <el-input v-model="ruleForm.password" autocomplete="off" placeholder="密码" type="text" />
			</el-form-item>
												 
			<!-- <el-form-item label="姓名" class="input" >
			  <el-input v-model="ruleForm.xingming" autocomplete="off" placeholder="姓名" type="text" />
			</el-form-item> -->
			   <!-- 此处要校检用户上传成功后返回的地址 -->
			   <el-form-item class="upload"  label="资质" prop="zizhi">
					<file-upload
					tip="点击上传资质"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.zizhi?ruleForm.zizhi:''"
					@change="zizhiUploadChange"
					></file-upload>
                 </el-form-item>
													 
			<!-- <el-form-item label="联系电话" class="input" >
			  <el-input v-model="ruleForm.lianxidianhua" autocomplete="off" placeholder="联系电话" type="text" />
			</el-form-item> -->
		  <div style="display: flex;flex-wrap: wrap;width: 100%;justify-content: center;">
			<el-button class="btn" type="primary" @click="login()">注册</el-button>
			<el-button class="btn close" type="primary" @click="close()">取消</el-button>
		  </div>
		</el-form>
		</div>
		</div> 
</div>
 
</template>
<script>
export default {
  data() {
    return {
		/* 控制上传对话框显示与否,默认false不显示 */
		dialogVisible:false,
            /* 对话框内放置的上传图片的地址 */
            dialogImageUrl:'',
            /* 已经上传的文件列表,本例未使用 */
            fileList:[],
 
      ruleForm: {
		zizhi: ''
              },
      tableName:"",
      rules: {}
    };
  },
  created() {
	this.addEditStyleChange()
	this.addEditUploadStyleChange()
  },
  mounted(){
    // let table = this.$storage.get("loginTable");
    this.tableName = "shangjia";
	
  },
  methods: {
	handleExceed(file,fileList){
            this.$message.error('最多上传一张头像...');
        },
        /* 上传之前的钩子此处多用来进行上传时的格式查询等等
        */
        beforeAvatarUpload(file){
            /* 匹配 MIME 格式 */
            const isJPG = file.type === 'image/jpeg';
            /* 这里表示不能大于500k */
            const isLt2M = file.size / 1024 / 1024 < 0.5;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
                
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 500k!');
            }
            return isJPG && isLt2M;
        },
        /* 
            文件上传成功时的钩子 
            response:上传成功返回的响应
            file:每次上传的文件对象
            fileList:目前所有已经上传的文件数组
        */
        handleAvatarSuccess(response, file, fileList){
            console.log(response,file,fileList)
            /* 一般在上传成功后在此处进行业务逻辑，例如将返回的路径添加进表单双向绑定的data中的对象 */
            /* 
                {
                    "code":null,
                    "flag":true,
                    "msg":"上传成功~",
                    "data":{
                        "fileId":null,
                        "fileName":"1",
                        "fileSize":48558,
                        "path":"http://etoak.vip:9528/4028823a74559fc80174559fc81e0000.jpg"
                    }
                }
            */
            if(response.flag){
                /* 将返回的路径添加进封装路径的表单项属性 */
                //this.ruleForm.paths.push(response.data.path)    
                /* 上传成功后将返回的路径赋值给表单项中封装的路径 */
                this.ruleForm.zizhi = 'http://localhost:8080/springboot1mi5m/upload/' + response.data.file
            }
        },
        /* 
            文件移除的钩子 
            file:点击的要删除的已经上传成功的文件对象
            fileList:目前所有已经上传的文件数组
        */
        handleRemove(file, fileList) {
            /* 此处进行上传文件被删除之后的逻辑 */
            //this.ruleForm.paths.splice(this.ruleForm.paths.indexOf(file.response.data.path),1)
            /* 将已经保存的上传成功的路径恢复为空串 */
            this.regForm.path = ''
        },
        /* 点击放大镜查看上传文件详细信息时执行 */
        handlePictureCardPreview(file) {
            /* 填充路径 */
            this.dialogImageUrl = this.regForm.path
            /* 对话框显示 */
            this.dialogVisible = true;
        },	  
    // 获取uuid
    getUUID () {
      return new Date().getTime();
    },
    close(){
	this.$router.push({ path: "/login" });
    },
	zizhiUploadChange(fileUrls) {
                this.ruleForm.zizhi = fileUrls;
            },
    // 注册
    login() {
                              if((!this.ruleForm.username) && `shangjia` == this.tableName){
        this.$message.error(`用户名不能为空`);
        return
      }
                                                                  if((!this.ruleForm.password) && `shangjia` == this.tableName){
        this.$message.error(`密码不能为空`);
        return
      }
    //                                                                                                                                                                                                                                                         if(`shangjia` == this.tableName && this.ruleForm.lianxidianhua&&(!this.$validate.isMobile(this.ruleForm.lianxidianhua))){
    //     this.$message.error(`联系电话应输入手机格式`);
    //     return
    //   }
                                                                                                                                                this.$http({
        url: `${this.tableName}/register`,
        method: "post",
        data:this.ruleForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "注册成功",
            type: "success",
            duration: 1500,
            onClose: () => {
              this.$router.replace({ path: "/login" });
            }
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
	.el-radio__input.is-checked .el-radio__inner {
		border-color: #00c292;
		background: #00c292;
	}

	.el-radio__input.is-checked .el-radio__inner {
		border-color: #00c292;
		background: #00c292;
	}

	.el-radio__input.is-checked .el-radio__inner {
		border-color: #00c292;
		background: #00c292;
	}

	.el-radio__input.is-checked+.el-radio__label {
		color: #00c292;
	}

	.el-radio__input.is-checked+.el-radio__label {
		color: #00c292;
	}

	.el-radio__input.is-checked+.el-radio__label {
		color: #00c292;
	}

	.h1 {
		margin-top: 10px;
	}

	body {
		padding: 0;
		margin: 0;
	}

	// .container {
 //    min-height: 100vh;
 //    text-align: center;
 //    // background-color: #00c292;
 //    padding-top: 20vh;
 //    background-image: url(../assets/img/bg.jpg);
 //    background-size: 100% 100%;
 //    opacity: 0.9;
 //  }

	// .login-form:before {
	// 	vertical-align: middle;
	// 	display: inline-block;
	// }

	// .login-form {
	// 	max-width: 500px;
	// 	padding: 20px 0;
	// 	width: 80%;
	// 	position: relative;
	// 	margin: 0 auto;

	// 	.label {
	// 		min-width: 60px;
	// 	}

	// 	.input-group {
	// 		max-width: 500px;
	// 		padding: 20px 0;
	// 		width: 80%;
	// 		position: relative;
	// 		margin: 0 auto;
	// 		display: flex;
	// 		align-items: center;

	// 		.input-container {
	// 			display: inline-block;
	// 			width: 100%;
	// 			text-align: left;
	// 			margin-left: 10px;
	// 		}

	// 		.icon {
	// 			width: 30px;
	// 			height: 30px;
	// 		}

	// 		.input {
	// 			position: relative;
	// 			z-index: 2;
	// 			float: left;
	// 			width: 100%;
	// 			margin-bottom: 0;
	// 			box-shadow: none;
	// 			border-top: 0px solid #ccc;
	// 			border-left: 0px solid #ccc;
	// 			border-right: 0px solid #ccc;
	// 			border-bottom: 1px solid #ccc;
	// 			padding: 0px;
	// 			resize: none;
	// 			border-radius: 0px;
	// 			display: block;
	// 			width: 100%;
	// 			height: 34px;
	// 			padding: 6px 12px;
	// 			font-size: 14px;
	// 			line-height: 1.42857143;
	// 			color: #555;
	// 			background-color: #fff;
	// 		}

	// 	}
	// }

	.nk-navigation {
		margin-top: 15px;

		a {
			display: inline-block;
			color: #fff;
			background: rgba(255, 255, 255, .2);
			width: 100px;
			height: 50px;
			border-radius: 30px;
			text-align: center;
			display: flex;
			align-items: center;
			margin: 0 auto;
			justify-content: center;
			padding: 0 20px;
		}

		.icon {
			margin-left: 10px;
			width: 30px;
			height: 30px;
		}
	}

	.register-container {
		margin-top: 10px;

		a {
			display: inline-block;
			color: #fff;
			max-width: 500px;
			height: 50px;
			border-radius: 30px;
			text-align: center;
			display: flex;
			align-items: center;
			margin: 0 auto;
			justify-content: center;
			padding: 0 20px;

			div {
				margin-left: 10px;
			}
		}
	}
	
	.container {
		background-image: url("http://codegen.caihongy.cn/20210228/c8db205ee3a44746a3113dace7bb9506.jpg");
		height: 100vh;
		background-position: center center;
		background-size: cover;
		background-repeat: no-repeat;
	
		.login-form {
			right: 50%;
			top: 50%;
			height: auto;
			transform: translate3d(50%, -50%, 0);
			border-radius: 10px;
			background-color: rgba(255,255,255,.5);
			width: 420px;
			padding: 30px 30px 40px 30px;
			font-size: 14px;
			font-weight: 500;
			
			.h1 {
				margin: 0;
				text-align: center;
				line-height: 54px;
			    font-size: 24px;
			    color: #000;
			}
				
			.rgs-form {
				display: flex;
				flex-direction: column;
				justify-content: center;
				align-items: center;
				
				.input {
					width: 100%;
					
					& /deep/ .el-form-item__label {
						line-height: 40px;
						color: rgba(17, 16, 16, 1);
						font-size: #606266;
					}
					
					& /deep/ .el-input__inner {
						height: 40px;
						color: rgba(23, 24, 26, 1);
						font-size: 14px;
						border-width: 1px;
						border-style: solid;
						border-color: #606266;
						border-radius: 22px;
						background-color: #fff;
					}
				}
				
				.btn {
					margin: 0 10px;
					width: 88px;
					height: 44px;
					color: #fff;
					font-size: 14px;
					border-width: 1px;
					border-style: solid;
					border-color: #409EFF;
					border-radius: 22px;
					background-color: #409EFF;
				}

				.close {
					margin: 0 10px;
					width: 88px;
					height: 44px;
					color: #409EFF;
					font-size: 14px;
					border-width: 1px;
					border-style: solid;
					border-color: #409EFF;
					border-radius: 22px;
					background-color: #FFF;
				}

			}
		}
	}
</style>
