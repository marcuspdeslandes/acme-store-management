import {
  CommandAction,
  SET_FILTER_VALUE,
  SET_NAME_TO_CHANGE_VALUE,
  SET_STORE_ID_TO_CHANGE,
  STORE_LIST_CHANGE_NAME_FAILURE,
  STORE_LIST_CHANGE_NAME_SUCCESS,
  STORE_LIST_FAILURE,
  STORE_LIST_SUCCESS,
} from './types';
import axios from "axios";

function failure(): CommandAction {
  return {
    type: STORE_LIST_FAILURE
  };
}

function success(payload: any): CommandAction {
  return {
    type: STORE_LIST_SUCCESS,
    payload
  };
}

function failureChangeName(): CommandAction {
  return {
    type: STORE_LIST_CHANGE_NAME_FAILURE
  };
}

function successChangeName(): CommandAction {
  return {
    type: STORE_LIST_CHANGE_NAME_SUCCESS
  };
}

export function onChangeCurrentFilterValue(payload: any): CommandAction {
  return {
    type: SET_FILTER_VALUE,
    payload
  };
}
export function onChangeCurrentStoreName(payload: any): CommandAction {
  return {
    type: SET_NAME_TO_CHANGE_VALUE,
    payload
  };
}

export function nameToChange(id: number, name: string): CommandAction {
  return {
    type: SET_STORE_ID_TO_CHANGE,
    payload: {
      id,
      name
    }
  };
}

export function changeName(storeId: number, name: string) {
  return async (dispatch: any, getState: () => any) => {
  let url = `http://localhost:8082/acme/store/v1/persist/store/id/`+storeId;

    axios.put(url, name , {
      headers: {'Access-Control-Allow-Origin': '*', 'Content-Type': 'application/json'}
    }).then(
        response => {
          dispatch(execute(getState().store.pagination))
          return dispatch(successChangeName());
        },
        rejection => {
          return dispatch(failureChangeName());
        }
    );
  }
}

export function execute(pagination?: any, filter?: any) {
  return async (dispatch: any, getState: () => any) => {

    let url = `http://localhost:8082/acme/store/v1/persist/store/`;
    if(pagination){
      url = `http://localhost:8082/acme/store/v1/persist/store/?page=${pagination.pageNumber}&pageSize=${pagination.pageSize}`;
      if (filter) {
        pagination.pageNumber = 0;
        url = `http://localhost:8082/acme/store/v1/persist/store/name/${filter}?page=${pagination.pageNumber}&pageSize=${pagination.pageSize}`;
      }
    }

    axios.get(url, {
      headers: {'Access-Control-Allow-Origin': '*'}
    }).then(
        response => {
          return dispatch(success(response.data));
        },
        rejection => {
          return dispatch(failure());
        }
    );
  }
}
