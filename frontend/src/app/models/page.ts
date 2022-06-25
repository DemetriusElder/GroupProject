import { Blog } from './blog';

export interface Page {
  content: Blog[];
  last: boolean;
}
