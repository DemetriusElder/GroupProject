import { Blog } from './blog';

export interface Pageable {
  content: Blog[];
  last: boolean;
}
