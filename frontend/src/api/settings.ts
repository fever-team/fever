import axios, { AxiosRequestConfig, AxiosResponse, AxiosError } from 'axios';


const api = axios.create();

/*
 * Make axios not to throw any error, because of status code.
 * Add duration checking.
 */
api.interceptors.request.use((config: AxiosRequestConfig): AxiosRequestConfig => {
  return {
    ...config,
    metadata: {
      startTime: Date.now(),
    },
    validateStatus: (status: number) => true,
  };
}, (error: AxiosError) => {
  return Promise.reject(error);
});

api.interceptors.response.use((response: AxiosResponse): AxiosResponse => {
  const startTime = response.config.metadata.startTime;
  const endTime = Date.now();

  response.config.metadata.endTime = endTime;
  response.config.metadata.requestDuration = endTime - startTime;
  return response;
});


export default api;