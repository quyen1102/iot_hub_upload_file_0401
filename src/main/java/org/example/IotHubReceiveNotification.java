package org.example;

import com.microsoft.azure.sdk.iot.service.*;

public class IotHubReceiveNotification {
    private static final String connectionString = "HostName=mot-so-cong-nghe-phat-trien-phan-mem.azure-devices.net;DeviceId=device0104;SharedAccessKey=8HDt1qqvwGreifWRJZnqe4TFp/2holWrlQ21b1YPpBM=";
    private static final IotHubServiceClientProtocol protocol = IotHubServiceClientProtocol.AMQPS;

    public static void main(String[] args) throws Exception
    {
        ServiceClient sc = ServiceClient.createFromConnectionString(connectionString, protocol);

        FileUploadNotificationReceiver receiver = sc.getFileUploadNotificationReceiver();
        receiver.open();
        FileUploadNotification fileUploadNotification = receiver.receive(2000);

        if (fileUploadNotification != null)
        {
            System.out.println("File Upload notification received");
            System.out.println("Device Id : " + fileUploadNotification.getDeviceId());
            System.out.println("Blob Uri: " + fileUploadNotification.getBlobUri());
            System.out.println("Blob Name: " + fileUploadNotification.getBlobName());
            System.out.println("Last Updated : " + fileUploadNotification.getLastUpdatedTimeDate());
            System.out.println("Blob Size (Bytes): " + fileUploadNotification.getBlobSizeInBytes());
            System.out.println("Enqueued Time: " + fileUploadNotification.getEnqueuedTimeUtcDate());
        }
        else
        {
            System.out.println("No file upload notification");
        }

        receiver.close();
    }

}
