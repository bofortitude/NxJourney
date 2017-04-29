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

/////////////////////////////////////////////////////////////////////////////////////////////
export const getUsLabResourcePlanIpAddress = params => { return axios.get(`${base}/userdata/uslabresourceplanipaddress/?${params}`); };
export const addUsLabResourcePlanIpAddress = params => { return axios.post(`${base}/userdata/uslabresourceplanipaddress/`, params ); };
export const removeUsLabResourcePlanIpAddress = id => { return axios.delete(`${base}/userdata/uslabresourceplanipaddress/${id}/`); };
export const editUsLabResourcePlanIpAddress = (id, params) => { return axios.put(`${base}/userdata/uslabresourceplanipaddress/${id}/`, params ); };

export const getUsLabResourcePlanHaId = params => { return axios.get(`${base}/userdata/uslabresourceplanhaid/?${params}`); };
export const addUsLabResourcePlanHaId = params => { return axios.post(`${base}/userdata/uslabresourceplanhaid/`, params ); };
export const removeUsLabResourcePlanHaId = id => { return axios.delete(`${base}/userdata/uslabresourceplanhaid/${id}/`); };
export const editUsLabResourcePlanHaId = (id, params) => { return axios.put(`${base}/userdata/uslabresourceplanhaid/${id}/`, params ); };

export const getUsLabResourcePlanVlan = params => { return axios.get(`${base}/userdata/uslabresourceplanvlan/?${params}`); };
export const addUsLabResourcePlanVlan = params => { return axios.post(`${base}/userdata/uslabresourceplanvlan/`, params ); };
export const removeUsLabResourcePlanVlan = id => { return axios.delete(`${base}/userdata/uslabresourceplanvlan/${id}/`); };
export const editUsLabResourcePlanVlan = (id, params) => { return axios.put(`${base}/userdata/uslabresourceplanvlan/${id}/`, params ); };

// export const getUsLabResourcePlanOspfId = params => { return axios.get(`${base}/userdata/uslabresourceplanospfid/?${params}`); };
// export const addUsLabResourcePlanOspfId = params => { return axios.post(`${base}/userdata/uslabresourceplanospfid/`, params ); };
// export const removeUsLabResourcePlanOspfId = id => { return axios.delete(`${base}/userdata/uslabresourceplanospfid/${id}/`); };
// export const editUsLabResourcePlanOspfId = (id, params) => { return axios.put(`${base}/userdata/uslabresourceplanospfid/${id}/`, params ); };


