from django.urls import path
from .views import SendEmailView, SendEmailTemplateView

urlpatterns = [
    path("send-email/", SendEmailView.as_view(), name="send-email"),
    path("send-email-template/", SendEmailTemplateView.as_view(), name="send-email-template"),
]
