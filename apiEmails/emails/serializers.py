from rest_framework import serializers

class EmailSerializer(serializers.Serializer):
    sender = serializers.EmailField()
    receptor = serializers.EmailField()
    password = serializers.CharField(write_only=True)
    subject = serializers.CharField()
    message = serializers.CharField()

class EmailTemplateSerializer(serializers.Serializer):
    sender = serializers.EmailField()
    receptor = serializers.EmailField()
    password = serializers.CharField(write_only=True)
    subject = serializers.CharField()
    template_dir = serializers.CharField()
    template_name = serializers.CharField()
    context = serializers.DictField()
