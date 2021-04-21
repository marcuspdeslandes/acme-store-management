import { StoreListResultRow } from './types';
import moment from 'moment';

export default (data: StoreListResultRow): StoreListResultRow => {
  return {
    id: data.id ? data.id : '',
    code: data ? data.code : '',
    description: data && data.description ? data.description.slice(0,20) : '',
    name: data ? data.name : '',
    openingDate: data ? formatDate(data.openingDate) : '',
    storeType: data ? data.storeType : '',
    storeSeason: data ? data.storeSeason : '',
    specialField1: data ? data.specialField1 : '',
    specialField2: data ? data.specialField2 : ''
  };
};

export const getLabels = (): any => ({
  id: 'Id',
  code: 'Code',
  description: 'Description',
  name: 'Name',
  openingDate: 'opening Date',
  storeType: 'Store Type',
  storeSeason: 'Season',
  SpecialField1: 'Special field 1',
  SpecialField2: 'Special field 2',


});

function formatDate(date: any, format?: string) {
  if (date) {
    const dateFormat = format || 'YYYY-MM-DD';
    return moment
        .utc(date)
        .format(dateFormat)
        .toString();
  }
  return '';
}
