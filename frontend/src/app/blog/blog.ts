export interface Blog {
  id?: string;
  imageUrl?: string;
  title?: string;
  author?: string;
  date?: Date;
  content?: string;
}
export interface AuthUser{
  id?: string;
  username?: string;
  password?: string;
  role?: string;
}
