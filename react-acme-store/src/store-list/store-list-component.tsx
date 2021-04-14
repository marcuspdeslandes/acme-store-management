import React, { Component, Fragment } from 'react';
import {getLabels} from "./redux/mapperStore";
import { StoreListState } from "./redux/types";
import { ChevronLeft, DoubleArrows } from '../assets/svg';

interface SidebarActionsProps {
  execute: (pagination?: any, filter?: any) => void;
  onChangeCurrentFilterValue: (filter?: any) => void;
  onChangeCurrentStoreName: (name: string) => void;
  changeName: (storeId: number, name: string) => void;
  nameToChange: (storeId: number, name: string) => void;
  store: StoreListState
}

export default class StoreListComponent extends Component<SidebarActionsProps> {
componentDidMount() {
  this.props.execute(this.props.store.pagination);
}
  onChangePage(page: any) {
    this.props.store.pagination.pageNumber = page;
    this.props.execute(this.props.store.pagination);
  }

  render() {
  const columns = getLabels();
    const { storeIdToChange, storeNameChanged, filter, pagination , content, totalElements } = this.props.store;

    return (
      <Fragment>
        <div className="tile is-flex-none is-child box">
            {storeIdToChange == 0 &&
              <div className="field has-addons is-pulled-right">
                <div className="control">
                  <input
                      className="input"
                      type="text"
                      placeholder="Search by name"
                      value={filter}
                      onChange={(event: any) => {
                        this.props.onChangeCurrentFilterValue(event.target.value);
                      }}
                  />
                </div>
                <div className="control">
                  <a className="button is-info" onClick={() => this.props.execute(pagination, filter)}>
                    Search
                  </a>
                </div>
              </div>}

          {storeIdToChange != 0 &&
          <div className="field has-addons is-pulled-right">
            <div className="control">
              <input
                  className="input has-icon"
                  type="text"
                  placeholder="Name"
                  value={storeNameChanged}
                  onChange={(event: any) => {
                    this.props.onChangeCurrentStoreName(event.target.value);
                  }}
              />
            </div>
            <div className="control">
              <a className="button is-info" onClick={() => this.props.changeName(storeIdToChange, storeNameChanged)}>
                Change name
              </a>
            </div>
          </div>}

          <table className="table is-striped is-fullwidth">
            <thead>
            <tr>
              <th colSpan={10} className="subtitle is-10">
                ACME Store List
              </th>
            </tr>
            </thead>
            <tbody>
            <tr>
              {columns &&
              Object.keys(columns).map((key, index) => (
                  <th className='flex-cell' key={index}>
                    {columns[key]}
                  </th>
              ))}
            </tr>

            {content &&
            content.map((row: any, index: number) => (
                <tr key={index}>
                  {Object.keys(row).map(
                      (value, key) => (
                          <td
                              className={`flex-cell`}
                              key={key}
                              onClick={() => this.props.nameToChange(row.id, row.name)}
                          >{row[value]}</td>
                      )
                  )}
                </tr>
            ))}
            </tbody>
          </table>
          <nav className="pagination is-centered" role="navigation" aria-label="pagination">
            <ul className="pagination-list">
              <li>
                <button
                    disabled={pagination && pagination.pageNumber === 0}
                    className="pagination-link"
                    onClick={() => this.onChangePage(0)}
                >
                  <DoubleArrows
                      className="icon icon-svg"
                      title="First page"
                  />
                </button>
              </li>

              <li>
                <button
                    disabled={pagination && pagination.pageNumber === 0}
                    className="pagination-previous"
                    onClick={() => this.onChangePage(pagination.pageNumber-1)}
                >
                  <ChevronLeft className="icon icon-svg" title="Previous page" />
                </button>
              </li>

              <li>
                <span className="pagination-label">
                  {`${pagination && pagination.pageNumber + 1}`.padStart(2, '0')}
                  <span className="has-padding-left-xs has-padding-right-xs"> of </span>
                  {`${totalElements}`.padStart(2, '0')}
                </span>
              </li>

              <li>
                <button
                    disabled={pagination && pagination.pageNumber === totalElements-1}
                    className="pagination-next"
                    onClick={() => this.onChangePage(pagination.pageNumber+1)}
                >
                  <ChevronLeft className="icon icon-svg icon-180" title="Next page" />
                </button>
              </li>

              <li>
                <button
                    disabled={pagination && pagination.pageNumber === totalElements-1}
                    className="pagination-link"
                    onClick={() => this.onChangePage(totalElements-1)}
                >
                  <DoubleArrows
                      className="icon icon-svg icon-180"
                      title="Last page"
                  />
                </button>
              </li>
            </ul>
          </nav>
        </div>
      </Fragment>
    );
  }
}
