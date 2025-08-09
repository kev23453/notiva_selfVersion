from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from utils.email_service import Email  # tu clase Email
from .serializers import EmailSerializer, EmailTemplateSerializer

class SendEmailView(APIView):
    def post(self, request):
        serializer = EmailSerializer(data=request.data)
        if serializer.is_valid():
            data = serializer.validated_data
            try:
                email_service = Email(
                    sender=data["sender"],
                    receptor=data["receptor"],
                    password=data["password"]
                )
                email_service.send_simple_message(
                    subject=data["subject"],
                    message=data["message"]
                )
                return Response({"status": "success",
                                "message": "Correo enviado correctamente"})
            except Exception as e:
                return Response({"status": "error",
                                "detail": str(e)}, status=500)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class SendEmailTemplateView(APIView):
    def post(self, request):
        serializer = EmailTemplateSerializer(data=request.data)
        if serializer.is_valid():
            data = serializer.validated_data
            try:
                email_service = Email(
                    sender=data["sender"],
                    receptor=data["receptor"],
                    password=data["password"]
                )
                email_service.send_templates_html(
                    subject=data["subject"],
                    path_template_dir=data["template_dir"],
                    path=data["template_name"],
                    context=data["context"]
                )
                return Response({"status": "success",
                                "message": "Correo con plantilla enviado correctamente"})
            except Exception as e:
                return Response({"status": "error", "detail": str(e)}, status=500)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
