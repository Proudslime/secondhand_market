import axios from 'axios'
import { Loading, Message } from 'element-ui'
import router from '../router/index.js'

let loading

function startLoading() {
	loading = Loading.service({
		lock:true,
		text:'加载中……',
	})
}

function endLoading() {
	loading.close()
}

axios.interceptors.request.use(
	(confing) => {
		startLoading()
		
		if (localStorage.eToken) {
			confing.headers.Autorization = localStorage.eToken
		}
		
		return confing
	},
	(error) => {
		return Promise.reject(error)
	}
)

axios.interceptors.response.use(
    (response) => {
        endLoading()
        return response
    },
    (error) => {
        Message.error(error.response.data)
        endLoading()

        // 获取状态码
        const { status } = error.response

        if (status === 401) {
            Message.error('请重新登录')
                //清楚token
            localStorage.removeItem('eToken')
                //跳转到登录页面
            router.push('/')
        }
        return Promise.reject(error)
    }
)
export default axios