import axios from '../http.js'
import QS from 'qs'
import base from '../base.js'

export function userLoginIn(data) {
	return axios({
		url: '${base.url}/userLogin',
		method: 'post',
		data: QS.stringfy(data)
	})
}