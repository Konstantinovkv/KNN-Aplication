from cassandra.cluster import Cluster
from cassandra.auth import PlainTextAuthProvider
import uuid
import random
import logging

def generate_data_points(n):
    labels = ['A', 'B', 'C']
    data_points = []

    for _ in range(n):
        feature1 = random.uniform(0, 10)
        feature2 = random.uniform(0, 10)
        label = random.choice(labels)
        data_points.append((feature1, feature2, label))

    return data_points

def insert_data_points(session, data_points):
    insert_query = '''
        INSERT INTO knn.data_points (id, feature_1, feature_2, label)
        VALUES (%s, %s, %s, %s);
    '''

    for data_point in data_points:
        feature1, feature2, label = data_point
        session.execute(insert_query, (uuid.uuid4(), feature1, feature2, label))

logging.basicConfig(level=logging.INFO)

def main():
    try:
        auth_provider = PlainTextAuthProvider(username='cassandra', password='cassandra')
        cluster = Cluster(['127.0.0.1'], auth_provider=auth_provider)
        session = cluster.connect()
    except Exception as e:
        logging.error(f"Error connecting to Cassandra: {e}")
        return

    try:
        data_points = generate_data_points(100)
        insert_data_points(session, data_points)
    except Exception as e:
        logging.error(f"Error inserting data points: {e}")
    else:
        logging.info("Inserted test data into the data_points table.")

    session.shutdown()
    cluster.shutdown()

if __name__ == '__main__':
    main()
