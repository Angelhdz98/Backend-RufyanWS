package com.example.PaginaWebRufyan.User.DTO;




public record UpdatePasswordCommand( Long userId ,String oldPassword,
                                      String newPassword,
                                     String newPasswordConfirmation) {
}
