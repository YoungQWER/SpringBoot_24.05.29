let backendHost;

const hostname = window && window.location && window.location.hostname;
//  window.location ==> localhost:8080/todo
//  window.location.hostname ===> localhost                

if(hostname === "localhost"){
	backendHost = "http://localhost:8181";
}

export const API_BASE_URL = `${backendHost}`;