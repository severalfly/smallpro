# _*_coding:utf-8_*_
from tensorflow.examples.tutorials.mnist import input_data
mnist = input_data.read_data_sets("MNIST_data/", one_hot=True)

import tensorflow as tf

# 开始定义模型
x = tf.placeholder(tf.float32, [None, 784])
W = tf.Variable(tf.zeros([784, 10]))
b = tf.Variable(tf.zeros([10]))
y = tf.nn.softmax(tf.matmul(x, W) + b)
# 模型定义完了


# 开始训练
y_ = tf.placeholder(tf.float32, [None, 10]) # 这是正确的输入
cross_entropy = tf.reduce_mean(-tf.reduce_sum(y_ * tf.log(y) , reduction_indices=[1]))

train_step = tf.train.GradientDescentOptimizer(0.5).minimize(cross_entropy)

sess = tf.InteractiveSession()

tf.global_variables_initializer().run()
for _ in range(10):
	batch_xs, batch_ys = mnist.train.next_batch(100)
	sess.run(train_step, feed_dict={x:batch_xs, y_:batch_ys})
# 训练结束


# 开始验证
correct_prediction = tf.equal(tf.argmax(y,1), tf.argmax(y_,1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))

print(sess.run(accuracy, feed_dict={x:mnist.test.images, y_:mnist.test.labels}))
# 结束验证

'''
建立的是一个线性模型，就是一阶方程；
训练的目标是将平方和最小化，即 cross_entropy 最小
从目标数据中每次取100条记录，进行训练
验证是将模型的输出与正确的结果作对比
'''


# 权重初始化
def weight_variable(shape):
	initial = tf.truncated_normal(shape, stddev=0.1)
	return tf.Variable(initial)

def bias_variable(shape):
	initial = tf.constant(0.1, shape=shape)
	return tf.Variable(initial)

def conv2d(x, W):
	return tf.nn.conv2d(x, W, strides=[1,1,1,1], padding='SAME')

def max_pool_2x2(x):
	return tf.nn.max_pool(x, ksize=[1,2,2,1], strides=[1,2,2,1], padding='SAME')


# 第一层卷积
# 现在我们可以开始实现第一层了。它由一个卷积接一个max pooling 完成。
# 卷积在每个5*5的patch中算出32个特征。卷积的权重张量是`[5,5,1,32]`，
# 前面两个维度是patch的大小，接着是输入的通道数目，最后输出的通道数目。
# 而对于每一个输出通道都有一个对应的偏置量。
W_conv1 = weight_variable([5,5,1,32])
b_conv1 = bias_variable([32])

# 为了用这一层，我们把x 变成一个4d向量，其第2、第3维对应图片的宽、高，
# 最后一维代表图片的颜色通道数
x_image = tf.reshape(x, [-1, 28,28, 1])

# 我们把x_image 和权值向量进行卷积，加上偏置项，然后应用ReLu激活函数，最后进行max pooling
h_conv1 = tf.nn.relu(conv2d(x_image, W_conv1) + b_conv1)
h_pool1 = max_pool_2x2(h_conv1)



# 第二层卷积
# 为了构建一个更深的网络，我们会把几个类似的层堆叠起来。
# 第二层，每个5X5的batch 会得到64个特征
W_conv2 = weight_variable([5,5,32, 64])
b_conv2 = bias_variable([64])

h_conv2 = tf.nn.relu(conv2d(h_pool1, W_conv2) + b_conv2)
h_pool2 = max_pool_2x2(h_conv2)



# 密集连接层
W_fc1 = weight_variable([7 * 7 * 64, 1024])
b_fc1 = bias_variable([1024])

h_pool2_flat = tf.reshape(h_pool2, [-1, 7 * 7 * 64])
h_fc1 = tf.nn.relu(tf.matmul(h_pool2_flat, W_fc1) + b_fc1)


keep_prob = tf.placeholder(tf.float32)
h_fc1_drop = tf.nn.dropout(h_fc1, keep_prob)

W_fc2 = weight_variable([1024, 10])
b_fc2 = bias_variable([10])

y_conv = tf.matmul(h_fc1_drop, W_fc2) + b_fc2


# 训练和评估模型

cross_entropy = -tf.reduce_sum(y_ * tf.log(y_conv))
train_step = tf.train.AdamOptimizer(1e-4).minimize(cross_entropy)
correct_prediction = tf.equal(tf.argmax(y_conv, 1), tf.argmax(y_, 1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))
sess.run(tf.initialize_all_variables())
for i in range(20000):
	batch = mnist.train.next_batch(50)
	if i % 100 == 0:
		train_accuracy = accuracy.eval(feed_dict={x:batch[0], y_:batch[1], keep_prob: 1.0})
		print('step %d, training accuracy %g' %(i, train_accuracy))
print('test accuracy %g' %accuracy.eval(feed_dict={x:mnist.test.images, y_:mnist.test.labels, keep_prob:1.0}))


