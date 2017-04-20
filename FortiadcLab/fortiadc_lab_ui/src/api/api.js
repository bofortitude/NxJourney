import axios from 'axios';

let base = '';

export const requestLogin = params => { return axios.post(`${base}/login`, params).then(res => res.data); };

export const getUserList = params => { return axios.get(`${base}/user/list`, { params: params }); };

export const getUserListPage = params => { return axios.get(`${base}/user/listpage`, { params: params }); };

export const removeUser = params => { return axios.get(`${base}/user/remove`, { params: params }); };

export const batchRemoveUser = params => { return axios.get(`${base}/user/batchremove`, { params: params }); };

export const editUser = params => { return axios.get(`${base}/user/edit`, { params: params }); };

export const addUser = params => { return axios.get(`${base}/user/add`, { params: params }); };

// export const getUsTopologySettings = params => { return axios.get(`${base}/userdata/ustopologysettings`, { params: params }); };

export const getUsTopologyData = params => { return axios.get(`${base}/userdata/uslabtopologydata/`, { params: params }); };

export const addTopologyItem = params => { return axios.post(`${base}/userdata/uslabtopologydata/`, params); };

export const editTopologyItem = (name, params) => { return axios.put(`${base}/userdata/uslabtopologydata/${name}/`, params); };

export const deleteTopologyItem = name => { return axios.delete(`${base}/userdata/uslabtopologydata/${name}/`); };

export const getUsLabSettings = () => {return axios.get(`${base}/userdata/uslabsettingsdata/`);}

export const getUsLabResourceList = params => { return axios.get(`${base}/userdata/uslabresourceplandata/?${params}`); };

export const addUsLabResourceRecord = params => { return axios.post(`${base}/userdata/uslabresourceplandata/`, params ); };

export const removeUsLabResourceRecord = id => { return axios.delete(`${base}/userdata/uslabresourceplandata/${id}/`); };

// export const batchRemoveUsLabResourceRecord = params => { return axios.get(`${base}/userdata/uslabresourceplan/batchremoverecord`, { params: params }); };

export const editUsLabResourceRecord = (id, params) => { return axios.put(`${base}/userdata/uslabresourceplandata/${id}/`, params ); };




