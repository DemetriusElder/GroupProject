export interface Blog {
  id: string;
  imageUrl: string;
  title: string;
  author: string;
  date: Date;
  content: string;
}

export interface BlogDto {
  id?: string;
  imageUrl: string;
  title: string;
  author?: string;
  username: string;
  content: string;
}
