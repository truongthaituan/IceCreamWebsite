import { User } from './user.model';

export class UserChangePassword {
    user: User;
    currentPassword: string;
    newPassword: string;
    confirmPassword: string;
}
